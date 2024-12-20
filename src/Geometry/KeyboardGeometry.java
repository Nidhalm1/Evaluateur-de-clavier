package Geometry;

import java.util.List;
import java.util.Map;

// Un record pour la géométrie du clavier
public record KeyboardGeometry(int lignes, int colonnes, List<Touche> touches) {}
