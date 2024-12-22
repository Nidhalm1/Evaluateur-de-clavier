package Geometry;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// Un record pour la géométrie du clavier
public record KeyboardGeometry(int lignes, int colonnes, List<Touche> touches) {

    public Optional<Touche> findToucheById(String toucheId) {
        return touches().stream().filter(t-> t.id().equals(toucheId)).findFirst();
    }
}
