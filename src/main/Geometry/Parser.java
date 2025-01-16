package Geometry;

import java.util.List;
import java.util.Map;

import Analyseur.CorpusManager;
import Analyseur.DateUpdater;
import Analyseur.KeyboardEvaluator;
import DispositionFonc.KeyboardLayout;

/**
 * Classe principale pour analyser et évaluer la géométrie du clavier.
 */
public class Parser {
    /**
     * Point d'entrée de l'application.     */
    public static void main(String[] args) {
        CorpusManager corpusManager = new CorpusManager();
        DateUpdater dateUpdater = new DateUpdater();
        corpusManager.addObserver(dateUpdater);
        KeyboardEvaluator test = new KeyboardEvaluator();
        Map<String, Integer> nGrammeMap = corpusManager.nGramme();
        KeyboardGeometry clavier = KeyboardGeometry.initialiserAttribut();
        KeyboardLayout mapping = KeyboardLayout.initialiserContenu();
        Map<String, List<Touche>> map = mapping.toucheCorrespondant(nGrammeMap, clavier);
        double eval = KeyboardEvaluator.Total_Score(test.EvalutionMapping(map, nGrammeMap),
                corpusManager.sizeofcontenu());
        // Séparateur simple
        System.out.println();
        System.out.println("======================================");
        System.out.println("          Resultat de l'evaluation    ");
        System.out.println("======================================");

        // Explication claire du score
        System.out.println("Note : Plus le score est plus grand que 0, meilleure est la disposition.");
        System.out.println();

        // Affichage du score total
        System.out.printf("-> Score total de cette disposition : %.4f%n", eval);

        // Analyse de la qualité de la disposition
        if (eval < 0) {
            System.out.println("[!] La disposition est consideree comme mauvaise.");
        } else if (eval == 0) {
            System.out.println("[~] La disposition est consideree comme moyenne.");
        } else {
            System.out.println("[✔] La disposition est parfaite !");
        }

        // Ligne vide pour séparer les blocs d'affichage
        System.out.println();

    }

}
