package Geometry;
// Un record pour représenter une Touche
public record Touche(String id, int ligne, int colonne, Doigt doigt) {
    public void afficher() {
        System.out.println("Touche: " + id + ", Ligne: " + ligne + ", Colonne: " + colonne + ", Doigt: " + doigt);
    }

    public static Doigt attributdoigt(int column ){
        switch (column) {
            case 0:
            return Doigt.AURICULAIRE_GAUCHE;
            case 1:
            return Doigt.AURICULAIRE_GAUCHE;
            case 2:
            return Doigt.ANNULAIRE_GAUCHE;
            case 3:
            return Doigt.MAJEUR_GAUCHE;
            case 4:
            return Doigt.INDEX_GAUCHE;
            case 5:
            return Doigt.INDEX_GAUCHE;
            case 6:
            return Doigt.INDEX_DROIT;
            case 7:
            return Doigt.INDEX_DROIT;
            case 8:
            return Doigt.MAJEUR_DROIT;
            case 9:
            return Doigt.ANNULAIRE_DROIT;
            case 10:
            return Doigt.AURICULAIRE_DROIT;
            case 11 :
            return Doigt.AURICULAIRE_DROIT;
            case 12 :
            return Doigt.AURICULAIRE_DROIT;
            case 13 :
            return Doigt.AURICULAIRE_DROIT;
            default:
                throw new AssertionError();
        }
    }
}

