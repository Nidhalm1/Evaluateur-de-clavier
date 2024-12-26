package Analyseur;

import Geometry.Touche;

public class MovementBasic implements Movement {
    @Override
    boolean isSameFingerBigram(Touche first, Touche second){
        return true;
    }
}
