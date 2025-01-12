package Geometry;

import java.util.List;
import java.util.Map;

import Analyseur.CorpusManager;
import Analyseur.KeyboardEvaluator;
import DispositionFonc.KeyboardLayout;

public class Parser {
    public static void main(String[] args) {
        CorpusManager corpusManager = new CorpusManager();
        KeyboardEvaluator test = new KeyboardEvaluator();
        Map<String, Integer> nGrammeMap = corpusManager.nGramme();
        KeyboardGeometry clavier = KeyboardGeometry.initialiserAttribut();
        KeyboardLayout mapping = KeyboardLayout.initialiserContenu();
        Map<String, List<Touche>> map = mapping.toucheCorrespondant(nGrammeMap, clavier);
        double eval = KeyboardEvaluator.Total_Score(test.EvalutionMapping(map, nGrammeMap),
                corpusManager.sizeofcontenu());
        System.out.println("Plus le score est proche de 0 plus la disposition est meilleur");
        System.out.println("Le score total de cette Disposition est de " + eval);
        if (eval < 0.5000) {
            System.out.println("La disposition est mauvaise");
        } else if (eval == 0.5000){
            System.out.println("La disposition est moyenne");
        }
        else{
            System.out.println("La disposition est parfaite");
        }

    }

}
