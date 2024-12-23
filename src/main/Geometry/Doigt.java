package Geometry;

import Geometry.Main;

public enum Doigt {
    AURICULAIRE_GAUCHE(Main.GAUCHE),
    ANNULAIRE_GAUCHE(Main.GAUCHE),
    MAJEUR_GAUCHE(Main.GAUCHE),
    INDEX_GAUCHE(Main.GAUCHE),
    INDEX_DROIT(Main.DROITE),
    MAJEUR_DROIT(Main.DROITE),
    ANNULAIRE_DROIT(Main.DROITE),
    AURICULAIRE_DROIT(Main.DROITE);
    private final Main main ;
    Doigt (Main main){
        this.main = main;
    }
    public Main main(){
        return main;
    }
}
