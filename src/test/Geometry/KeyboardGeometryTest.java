package Geometry;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class KeyboardGeometryTest {

    private static KeyboardGeometry keyboardGeometry;

    @BeforeAll
    public static void setup() {
        try {
            // Charger le fichier JSON depuis le dossier src/main/resources
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream is = KeyboardGeometryTest.class.getResourceAsStream("/Disposition.json");
            assertNotNull(is, "Le fichier Disposition.json n'a pas été trouvé dans src/main/resources.");
            keyboardGeometry = objectMapper.readValue(is, KeyboardGeometry.class);
        } catch (Exception e) {
            fail("Initialisation échouée avec l'exception : " + e.getMessage());
        }
    }

    @Test
    public void testDeserialization() {
        // Vérifie que keyboardGeometry est correctement désérialisé à partir du JSON
        assertNotNull(keyboardGeometry, "keyboardGeometry ne doit pas être null après la désérialisation.");
        assertEquals(4, keyboardGeometry.lignes(), "Le nombre de lignes devrait être 4.");
        assertEquals(13, keyboardGeometry.colonnes(), "Le nombre de colonnes devrait être 13.");
        assertEquals(5, keyboardGeometry.touches().size(), "Il devrait y avoir 5 touches.");
    }

    @Test
    public void testFindToucheByIdExists() {
        // Teste la présence d'une touche existante et vérifie ses propriétés
        Optional<Touche> toucheOpt = keyboardGeometry.findToucheById("K01");
        assertTrue(toucheOpt.isPresent(), "La touche avec l'ID 'K01' devrait être présente.");
        Touche touche = toucheOpt.get();
        assertEquals("K01", touche.id(), "L'ID de la touche devrait être 'K01'.");
        assertEquals(0, touche.ligne(), "La touche 'K01' devrait être à la ligne 0.");
        assertEquals(1, touche.colonne(), "La touche 'K01' devrait être à la colonne 1.");
        assertEquals(Doigt.ANNULAIRE_GAUCHE, touche.doigt(), "Le doigt de la touche 'K01' devrait être ANNULAIRE_GAUCHE.");
    }

    @Test
    public void testFindToucheByIdDoesNotExist() {
        // Vérifie qu'une touche inexistante n'est pas trouvée
        Optional<Touche> toucheOpt = keyboardGeometry.findToucheById("K99");
        assertFalse(toucheOpt.isPresent(), "La touche avec l'ID 'K99' ne devrait pas être présente.");
    }

    @Test
    public void testEnumMapping() {
        // Vérifie le mappage des énumérations pour une touche spécifique
        Optional<Touche> toucheOpt = keyboardGeometry.findToucheById("KSHIFT");
        assertTrue(toucheOpt.isPresent(), "La touche 'KSHIFT' devrait être présente.");
        Touche touche = toucheOpt.get();
        assertEquals(Doigt.AURICULAIRE_GAUCHE, touche.doigt(), "Le doigt de 'KSHIFT' devrait être AURICULAIRE_GAUCHE.");
        assertEquals(Main.GAUCHE, touche.doigt().main(), "La main associée devrait être GAUCHE.");
    }

    @Test
    public void testInvalidJsonDeserialization() {
        // Vérifie que la désérialisation échoue avec un JSON invalide
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String invalidJson = "{ \"lignes\": \"quatre\", \"colonnes\": 13, \"touches\": [] }";
            objectMapper.readValue(invalidJson, KeyboardGeometry.class);
            fail("La désérialisation devrait échouer en raison d'un type incorrect pour 'lignes'.");
        } catch (Exception e) {
            assertTrue(e instanceof com.fasterxml.jackson.databind.exc.MismatchedInputException,
                    "L'exception devrait être une MismatchedInputException.");
        }
    }
}
