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
        return new KeyboardGeometry(lignes, colonnes, touches);
    }
}
