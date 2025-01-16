package Analyseur;

import Geometry.Doigt;
import Geometry.Touche;

/**
 * Classe utilitaire fournissant différentes méthodes 
 * pour analyser les mouvements des doigts sur le clavier.
 */
public  class MovementBasic implements MovementAnalyzer {

    /**
     * Vérifie si deux touches utilisent le même doigt.
     * @param first Première touche
     * @param second Seconde touche
     * @return true si le doigt est le même, false sinon
     */
    @Override
    public  boolean isSameFingerBigram(Touche first, Touche second) {
        return first.doigt() == second.doigt();
    }

    /**
     * Évalue un pourcentage lié à la main utilisée.
     * @param first Première touche
     * @param second Seconde touche
     * @return true si le doigt est le même, false sinon
     */
    public  boolean pourcentageMainevalute(Touche first, Touche second) {
        return first.doigt() == second.doigt();
    }

    /**
     * Vérifie un mouvement de type "ciseaux".
     */
    @Override
    public  boolean is_Cissors(Touche first, Touche second) {
        return ((first.doigt().main() == second.doigt().main()) && ((first.ligne() != second.ligne())));
    }

    /**
     * Vérifie l'écart LSB (Left-Shift-Big).
     */
    public  boolean is_LSB(Touche first, Touche second) {
        return ((first.colonne() != first.doigt().getRest_position().getColumn())
                || (second.colonne()) != second.doigt().getRest_position().getColumn());
    }

    /**
     * Vérifie un mouvement de changement de main.
     */
    public  boolean is_Switching(Touche first, Touche second) {
        return (first.doigt().main() != second.doigt().main());
    }

    /**
     * Vérifie un mouvement de type "bearing".
     */
    public  boolean is_Bearing(Touche first, Touche second) {
        return (!is_LSB(first, second) && !is_Cissors(first, second) &&
                !isSameFingerBigram(first, second) && !is_Switching(first, second)
                && (first.doigt() != second.doigt()));
    }

    /**
     * Vérifie un mouvement de redirection.
     */
    public  boolean is_Redirection(Touche first, Touche second, Touche third) {
        return ((first.doigt().main() == second.doigt().main()) && (second.doigt().main() == third.doigt().main()) &&
                (((first.doigt().getOrder() < second.doigt().getOrder())
                        && (second.doigt().getOrder() > third.doigt().getOrder())) ||
                        ((first.doigt().getOrder() > second.doigt().getOrder())
                                && (second.doigt().getOrder() < third.doigt().getOrder()))));
    }

    /**
     * Vérifie la pire redirection.
     */
    public  boolean worst_Redirection(Touche first, Touche second, Touche third) {
        return (is_Redirection(first, second, third)
                && (first.doigt() != Doigt.INDEX_GAUCHE && first.doigt() != Doigt.INDEX_DROIT)
                && (second.doigt() != Doigt.INDEX_GAUCHE && second.doigt() != Doigt.INDEX_DROIT) &&
                (third.doigt() != Doigt.INDEX_GAUCHE && third.doigt() != Doigt.INDEX_DROIT));
    }

    /**
     * Vérifie un mouvement de type SKS.
     */
    public  boolean is_SKS(Touche first, Touche second, Touche third) {
        return (isSameFingerBigram(first, third) && !isSameFingerBigram(first, second));
    }
}