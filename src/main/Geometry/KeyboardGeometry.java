package Geometry;

import java.io.File;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Record représentant la géométrie d'un clavier.
 *
 * @param lignes le nombre de lignes du clavier
 * @param colonnes le nombre de colonnes du clavier
 * @param touches la liste des touches du clavier
 */
public record KeyboardGeometry(int lignes, int colonnes, List<Touche> touches) {

    /**
     * Constructeur compact du record.
     */
    public KeyboardGeometry {
    }

    /**
     * Initialise les attributs du clavier à partir d'un fichier JSON.
     *
     * @return une instance de KeyboardGeometry ou null en cas d'erreur
     */
    public static KeyboardGeometry initialiserAttribut() {
        try {
            String fichierJson = "src/main/resources/Disposition.json";
            ObjectMapper objectMapper = new ObjectMapper();
                        KeyboardGeometry raw = objectMapper.readValue(new File(fichierJson), KeyboardGeometry.class);

            KeyboardGeometryBuilder builder = new KeyboardGeometryBuilder()
                    .withLignes(raw.lignes())
                    .withColonnes(raw.colonnes());

            // Ajouter les touches lues
            for (Touche t : raw.touches()) {
                builder.addTouche(t);
            }
            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Trouve une touche par son identifiant.
     *
     * @param toucheId l'identifiant de la touche
     * @return un Optional contenant la touche si trouvée, sinon vide
     */
    public Optional<Touche> findToucheById(String toucheId) {
        return touches().stream().filter(t -> t.id().equals(toucheId)).findFirst();
    }

}
