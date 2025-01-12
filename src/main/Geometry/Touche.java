package Geometry;

/**
 * Un record représentant une touche de clavier avec son identifiant,
 * sa position (ligne, colonne) et son doigt associé.
 */
public record Touche(String id, int ligne, int colonne, Doigt doigt) {

    /**
     * Affiche les informations de la touche dans la console.
     */
    public void afficher() {
        System.out.println("Touche: " + id + ", Ligne: " + ligne + ", Colonne: " + colonne + ", Doigt: " + doigt);
    }

    /**
     * This method maps a given column index to a corresponding finger (Doigt).
     * The mapping is based on a predefined set of rules.
     * 
     * @param column the index of the column to be mapped to a finger
     * @return the corresponding Doigt (finger) for the given column index
     * @throws AssertionError if the column index is out of the predefined range (0-13)
     * 
     * @implNote This method is for improvement purposes but is not yet finalized.
     */
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
            case 11:
                return Doigt.AURICULAIRE_DROIT;
            case 12:
                return Doigt.AURICULAIRE_DROIT;
            case 13:
                return Doigt.AURICULAIRE_DROIT;
            default:
                throw new AssertionError();
        }
    }
}

