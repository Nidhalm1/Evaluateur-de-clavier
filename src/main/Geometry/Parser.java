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
        for (Map.Entry<String, List<Touche>> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            for (Touche touche : entry.getValue()) {
                touche.afficher();
            }
            System.out.println();
        }
        System.out.println("Le score total de cette Disposition est de " + eval);
    }

}
