package Geometry;

import java.io.File;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

// Un record pour la géométrie du clavier
public record KeyboardGeometry(int lignes, int colonnes, List<Touche> touches) {

    public KeyboardGeometry {
    }

    public static KeyboardGeometry initialiserAttribut() {
        try {
            String fichierJson = "src/main/resources/Disposition.json";
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(fichierJson), KeyboardGeometry.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<Touche> findToucheById(String toucheId) {
        return touches().stream().filter(t -> t.id().equals(toucheId)).findFirst();
    }

}
