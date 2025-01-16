package Geometry;

import java.util.ArrayList;
import java.util.List;

public class KeyboardGeometryBuilder {
    private int lignes;
    private int colonnes;
    private List<Touche> touches = new ArrayList<>();

    // Constructeur par défaut (facultatif)
    public KeyboardGeometryBuilder() {
    }

    public KeyboardGeometryBuilder withLignes(int lignes) {
        this.lignes = lignes;
        return this;
    }

    public KeyboardGeometryBuilder withColonnes(int colonnes) {
        this.colonnes = colonnes;
        return this;
    }

    public KeyboardGeometryBuilder addTouche(Touche touche) {
        this.touches.add(touche);
        return this;
    }

    // Méthode qui construit l'objet final
    public KeyboardGeometry build() {
        if (lignes >= 6) {
            throw new IllegalArgumentException("Le nombre de lignes doit être inferieur à 7");
        }
        if (colonnes >= 6) {
            throw new IllegalArgumentException("Le nombre de colonnes doit être inferieur à 7");
        }
        return new KeyboardGeometry(lignes, colonnes, touches);
    }
}
