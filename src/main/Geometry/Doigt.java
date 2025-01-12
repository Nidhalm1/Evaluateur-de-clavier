package Geometry;

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
