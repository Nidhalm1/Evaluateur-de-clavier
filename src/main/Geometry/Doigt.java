package Geometry;

public enum Doigt {
    AURICULAIRE_GAUCHE(Main.GAUCHE, 0, new Rest(1, 0)),
    ANNULAIRE_GAUCHE(Main.GAUCHE, 1, new Rest(1, 1)),
    MAJEUR_GAUCHE(Main.GAUCHE, 2, new Rest(1, 2)),
    INDEX_GAUCHE(Main.GAUCHE, 3, new Rest(1, 3)),
    INDEX_DROIT(Main.DROITE, 3, new Rest(1, 6)),
    MAJEUR_DROIT(Main.DROITE, 2, new Rest(1, 7)),
    ANNULAIRE_DROIT(Main.DROITE, 1, new Rest(1, 8)),
    AURICULAIRE_DROIT(Main.DROITE, 0, new Rest(1, 9));

    private final Main main;

    public Rest getRest_position() {
        return rest_position;
    }

    private final Rest rest_position;

    public int getOrder() {
        return order;
    }

    private final int order;

    Doigt(Main main, int order, Rest pos) {
        this.main = main;
        this.order = order;
        this.rest_position = pos;
    }

    public Main main() {
        return main;
    }

}
