package Analyseur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Geometry.Main;
import Geometry.Touche;

public class KeyboardEvaluator {
        private MovementAnalyzer movDelegation ;
        public KeyboardEvaluator(MovementAnalyzer movDelegation){
            this.movDelegation = movDelegation;
        }
    /**
     * Évalue la différence d'utilisation entre la main gauche et la main droite.
     *
     * @param toucheCorrspond Association entre n-gramme et touches
     * @param ngramme Fréquences des n-grammes
     * @return Différence absolue entre main gauche et main droite
     */
    public long eval_PourcentageMain(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] mainGauche = { 0 };
        final int[] mainDroite = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 1) {
                if (touches.get(0).doigt().main() == Main.GAUCHE) {
                    mainGauche[0] += ngramme.get(xgramme);
                } else if (touches.get(0).doigt().main() == Main.GAUCHE) {
                    mainDroite[0] += ngramme.get(xgramme);
                }
            }

        });
        return Math.abs(mainGauche[0] - mainDroite[0]);
    }

    /**
     * Détecte et compte les bigrammes effectués avec le même doigt.
     *
     * @param toucheCorrspond Association entre n-gramme et touches
     * @param ngramme Fréquences des n-grammes
     * @return Nombre de bigrammes réalisés avec le même doigt
     */
    public long eval_SFB_mouv(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final long[] sameFinger = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 2) {
                if (movDelegation.isSameFingerBigram(touches.get(0), touches.get(1))) {
                    sameFinger[0] += ngramme.get(xgramme);
                }
            }

        });
        return sameFinger[0];
    }

    /**
     * Évalue les bigrammes type LSB.
     */
    public long eval_LSB_mouv(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] LSB = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 2) {
                if (movDelegation.is_LSB(touches.get(0), touches.get(1))) {
                    LSB[0] += ngramme.get(xgramme);
                }
            }

        });
        return LSB[0];
    }

    /**
     * Compte le nombre de bigrammes de type 'ciseaux'.
     */
    public long eval_Cissors(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] is_Cissors = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 2) {
                if (movDelegation.is_Cissors(touches.get(0), touches.get(1))) {
                    is_Cissors[0] += ngramme.get(xgramme);
                }
            }

        });
        return is_Cissors[0];
    }

    /**
     * Détermine les bigrammes de type 'switch'.
     */
    public long eval_is_Switch(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] Switch = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 2) {
                if (movDelegation.is_Switching(touches.get(0), touches.get(1))) {
                    Switch[0] += ngramme.get(xgramme);
                }
            }

        });
        return Switch[0];
    }

    /**
     * Repère le mouvement 'bearing' dans les bigrammes.
     */
    public long eval_Bearing(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] bearing = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 2) {
                if (movDelegation.is_Bearing(touches.get(0), touches.get(1))) {
                    bearing[0] += ngramme.get(xgramme);
                }
            }

        });
        return bearing[0];
    }

    /**
     * Évalue la redirection au sein des trigrammes.
     */
    public long eval_redirection(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] redirec = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 3) {
                if (movDelegation.is_Redirection(touches.get(0), touches.get(1), touches.get(2))) {
                    redirec[0] += ngramme.get(xgramme);
                }
            }

        });
        return redirec[0];
    }

    /**
     * Repère la pire forme de redirection dans les trigrammes.
     */
    public long eval_worst_redirection(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] ret = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 3) {
                if (movDelegation.worst_Redirection(touches.get(0), touches.get(1), touches.get(2))) {
                    ret[0] += ngramme.get(xgramme);
                }
            }

        });
        return ret[0];
    }

    /**
     * Détecte si un trigramme forme un schéma SKS.
     */
    public long eval_SKS(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] ret = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 3) {
                if (movDelegation.is_SKS(touches.get(0), touches.get(1), touches.get(2))) {
                    ret[0] += ngramme.get(xgramme);
                }
            }

        });
        return ret[0];
    }

    /**
     * Regroupe toutes les évaluations dans une même map et écrit les résultats dans un CSV.
     */
    public Map<String, Long> EvalutionMapping(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        Map<String, Long> ret = new HashMap<>();
        ret.put("SFB", eval_SFB_mouv(toucheCorrspond, ngramme));
        ret.put("LSB", eval_LSB_mouv(toucheCorrspond, ngramme));
        ret.put("Cissors", eval_Cissors(toucheCorrspond, ngramme));
        ret.put("SWITCH", eval_is_Switch(toucheCorrspond, ngramme));
        ret.put("BEARING", eval_Bearing(toucheCorrspond, ngramme));
        ret.put("REDIRECTION", eval_redirection(toucheCorrspond, ngramme));
        ret.put("WORST", eval_worst_redirection(toucheCorrspond, ngramme));
        ret.put("SKS", eval_SKS(toucheCorrspond, ngramme));
        ret.put("MainEval", eval_SKS(toucheCorrspond, ngramme));
        CorpusManager.writeMapinCSV(ret, "../resources/EvaluationMapping.csv");
        return ret;

    }

    /**
     * Calcule le score total en fonction des fréquences et d'un facteur de pondération.
     */
    public static double Total_Score(Map<String, Long> map_eval, int corpus_length) {
        double ret = 0;
        for (Map.Entry<String, Long> entry : map_eval.entrySet()) {
            ret += (entry.getValue() * Score.correspond_score(entry.getKey()));

        }
        return (ret / corpus_length);

    }
}