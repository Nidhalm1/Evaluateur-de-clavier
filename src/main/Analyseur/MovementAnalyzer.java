package Analyseur;

import Geometry.Touche;

public interface MovementAnalyzer {
    boolean isSameFingerBigram(Touche first, Touche second);
    boolean is_Cissors(Touche first, Touche second);
    boolean pourcentageMainevalute(Touche first, Touche second);
    boolean is_LSB(Touche first, Touche second);
    boolean is_Switching(Touche first, Touche second);
    boolean is_Bearing(Touche first, Touche second);
    boolean is_Redirection(Touche first, Touche second, Touche third);
    boolean worst_Redirection(Touche first, Touche second, Touche third);
    boolean is_SKS(Touche first, Touche second, Touche third);
}
