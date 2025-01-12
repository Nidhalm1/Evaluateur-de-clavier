package Geometry;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class KeyboardGeometryTest {

    // ...existing code...

    /**
     * Vérifie l'attribution correcte d'un doigt à une colonne de touche valide.
     */
    @Test
    void testAttributdoigt() {
        // Arrange
        int colonne = 4;
        Doigt attendu = Doigt.INDEX_GAUCHE;
        
        // Act
        Doigt resultat = Touche.attributdoigt(colonne);
        
        // Assert
        assertEquals(attendu, resultat);
    }

    /**
     * Vérifie qu'une colonne invalide déclenche une AssertionError.
     */
    @Test
    void testAttributdoigtInvalidColumn() {
        // Arrange
        int colonneInvalide = 14;

        // Act & Assert
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            Touche.attributdoigt(colonneInvalide);
        });

        String attendu = "java.lang.AssertionError";
        assertTrue(exception.toString().contains(attendu));
    }

    /**
     * Vérifie les getters de l'énumération Doigt.
     */
    @Test
    void testDoigtGetters() {
        // Arrange
        Doigt doigt = Doigt.INDEX_GAUCHE;

        // Act
        Main main = doigt.main();
        Rest rest = doigt.getRest_position();
        int order = doigt.getOrder();

        // Assert
        assertEquals(Main.GAUCHE, main);
        assertNotNull(rest);
        assertEquals(2, rest.getLine());
        assertEquals(4, rest.getColumn());
        assertEquals(3, order);
    }

    /**
     * Vérifie les champs du record Touche.
     */
    @Test
    void testToucheRecord() {
        // Arrange
        Touche touche = new Touche("B", 2, 5, Doigt.INDEX_GAUCHE);

        // Act & Assert
        assertEquals("B", touche.id());
        assertEquals(2, touche.ligne());
        assertEquals(5, touche.colonne());
        assertEquals(Doigt.INDEX_GAUCHE, touche.doigt());
    }

    /**
     * Vérifie la méthode afficher() pour une seule touche.
     */
    @Test
    void testAfficher() {
        // Arrange
        Touche touche = new Touche("A", 1, 4, Doigt.INDEX_GAUCHE);
        ByteArrayOutputStream sortie = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(sortie));
        
        try {
            // Act
            touche.afficher();
            
            // Assert
            String attendu = "Touche: A, Ligne: 1, Colonne: 4, Doigt: INDEX_GAUCHE" + System.lineSeparator();
            assertEquals(attendu, sortie.toString());
        } finally {
            // Restaurer le flux de sortie original
            System.setOut(originalOut);
        }
    }

    /**
     * Vérifie l'affichage de plusieurs touches successives.
     */
    @Test
    void testAffichageMultipleTouches() {
        // Arrange
        Touche touche1 = new Touche("C", 3, 6, Doigt.INDEX_DROIT);
        Touche touche2 = new Touche("D", 4, 7, Doigt.MAJEUR_DROIT);
        ByteArrayOutputStream sortie = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(sortie));

        try {
            // Act
            touche1.afficher();
            touche2.afficher();

            // Assert
            String attendu = "Touche: C, Ligne: 3, Colonne: 6, Doigt: INDEX_DROIT" + System.lineSeparator() +
                             "Touche: D, Ligne: 4, Colonne: 7, Doigt: MAJEUR_DROIT" + System.lineSeparator();
            assertEquals(attendu, sortie.toString());
        } finally {
            // Restaurer le flux de sortie original
            System.setOut(originalOut);
        }
    }

}

