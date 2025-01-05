package Analyseur;

import Geometry.Doigt;
import Geometry.Touche;

public final class MovementBasic {

    private MovementBasic() {
        throw new UnsupportedOperationException("Cette classe ne doit pas être instanciée.");
    }

    public static boolean isSameFingerBigram(Touche first, Touche second) {
        return first.doigt() == second.doigt();

    }

    public static boolean is_Cissors(Touche first, Touche second) {
        return ((first.doigt().main() == second.doigt().main()) && ((first.ligne() != second.ligne())));
    }

    public static boolean is_LSB(Touche first, Touche second) {
        return ((first.colonne() != first.doigt().getRest_position().getColumn())
                || (second.colonne()) != second.doigt().getRest_position().getColumn());

    }

    public static boolean is_Switching(Touche first, Touche second) {
        return (first.doigt().main() != second.doigt().main());
    }

    public static boolean is_Bearing(Touche first, Touche second) {
        return (!is_LSB(first, second) && !is_Cissors(first, second) &&
                !isSameFingerBigram(first, second) && !is_Switching(first, second)
                && (first.doigt() != second.doigt()));
    }

    public static boolean is_Redirection(Touche first, Touche second, Touche third) {

        return ((first.doigt().main() == second.doigt().main()) && (second.doigt().main() == third.doigt().main()) &&
                (((first.doigt().getOrder() < second.doigt().getOrder())
                        && (second.doigt().getOrder() > third.doigt().getOrder())) ||
                        ((first.doigt().getOrder() > second.doigt().getOrder())
                                && (second.doigt().getOrder() < third.doigt().getOrder()))));
    }

    public static boolean worst_Redirection(Touche first, Touche second, Touche third) {
        return (is_Redirection(first, second, third)
                && (first.doigt() != Doigt.INDEX_GAUCHE && first.doigt() != Doigt.INDEX_DROIT)
                && (second.doigt() != Doigt.INDEX_GAUCHE && second.doigt() != Doigt.INDEX_DROIT) &&
                (third.doigt() != Doigt.INDEX_GAUCHE && third.doigt() != Doigt.INDEX_DROIT));
    }

    public static boolean is_SKS(Touche first, Touche second, Touche third) {
        return (isSameFingerBigram(first, third) && !isSameFingerBigram(first, second));

    }

    public static void main(String[] args) {
        Touche test= new Touche("k30", 0, 9, Doigt.AURICULAIRE_DROIT);
        Touche test_2= new Touche("k31", 0, 8, Doigt.ANNULAIRE_DROIT);
        System.out.println(is_LSB(test, test_2));
        
    }
}