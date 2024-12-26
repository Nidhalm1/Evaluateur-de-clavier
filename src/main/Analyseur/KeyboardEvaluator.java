package Analyseur;

import java.util.List;

import Geometry.Touche;
import java.util.Map;

public class KeyboardEvaluator {
    private final Movement mouvement;
    public KeyboardEvaluator(Movement mouvement){
        this.mouvement = mouvement;
    }
    public Movement getMouvement() {
        return mouvement;
    }

    public long evaluerNgramme(Map<String,List<Touche>> toucheCorrspond , Map<String,Integer> ngramme){
        final int[] sameFinger = {0};
        
        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            for (int i = 0; i < len-1; i++) {
                if (mouvement.isSameFingerBigram(touches.get(i),touches.get(i+1))) {
                    sameFinger[0]+= ngramme.get(xgramme);
                }
            }
        });
        return 15;
    }
}
