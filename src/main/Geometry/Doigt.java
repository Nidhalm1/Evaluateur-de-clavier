package Geometry;

/**
 * Énumération représentant les doigts avec leurs positions sur le clavier.
 */
public enum Doigt {
    AURICULAIRE_GAUCHE(Main.GAUCHE, 0, new Rest(2, 1)),
    ANNULAIRE_GAUCHE(Main.GAUCHE, 1, new Rest(2, 2)),
    MAJEUR_GAUCHE(Main.GAUCHE, 2, new Rest(2, 3)),
    INDEX_GAUCHE(Main.GAUCHE, 3, new Rest(2, 4)),
    POUCE_GAUCHE(Main.GAUCHE,4,new Rest(4,4)),
    POUCE_DROIT(Main.DROITE,4,new Rest(4,7)),
    INDEX_DROIT(Main.DROITE, 3, new Rest(2, 7)),
    MAJEUR_DROIT(Main.DROITE, 2, new Rest(2, 8)),
    ANNULAIRE_DROIT(Main.DROITE, 1, new Rest(2, 9)),
    AURICULAIRE_DROIT(Main.DROITE, 0, new Rest(2, 10));

    private final Main main;
    private final Rest rest_position;
    private final int order;

    /**
     * Obtient la position restante du doigt.
     *
     * @return la position restante
     */
    public Rest getRest_position() {
        return rest_position;
    }

    /**
     * Obtient l'ordre du doigt.
     *
     * @return l'ordre du doigt
     */
    public int getOrder() {
        return order;
    }

    /**
     * Constructeur de l'énumération Doigt.
     *
     * @param main la main associée au doigt
     * @param order l'ordre du doigt
     * @param pos la position restée du doigt
     */
    Doigt(Main main, int order, Rest pos) {
        this.main = main;
        this.order = order;
        this.rest_position = pos;
    }

    /**
     * Obtient la main associée au doigt.
     *
     * @return la main (GAUCHE ou DROITE)
     */
    public Main main() {
        return main;
    }
}
