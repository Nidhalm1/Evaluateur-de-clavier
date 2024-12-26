package Geometry;
import java.util.List;
import java.util.Map;

import Analyseur.*;
import DispositionFonc.KeyboardLayout;
public class Parser {

    
    public static void main(String[] args) {
        CorpusManager corpusManager = new CorpusManager();
        Map<String, Integer> nGrammeMap = corpusManager.nGramme();
        KeyboardGeometry clavier = KeyboardGeometry.initialiserAttribut();
        KeyboardLayout mapping = KeyboardLayout.initialiserContenu();
        Map<String, List<Touche>> map = mapping.toucheCorrespondant(nGrammeMap, clavier);
        for (Map.Entry<String, List<Touche>> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            for (Touche touche : entry.getValue()) {
                touche.afficher();
            }
            System.out.println();
        }
    }
}
