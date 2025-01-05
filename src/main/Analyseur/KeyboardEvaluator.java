package Analyseur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Geometry.Touche;

public class KeyboardEvaluator {

    public long eval_SFB_mouv(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final long[] sameFinger = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len - 1 == 2) {
                if (MovementBasic.isSameFingerBigram(touches.get(0), touches.get(1))) {
                    sameFinger[0] += ngramme.get(xgramme);
                }
            }

        });
        return sameFinger[0];
    }

    public long eval_LSB_mouv(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] LSB = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len - 1 == 2) {
                if (MovementBasic.is_LSB(touches.get(0), touches.get(1))) {
                    LSB[0] += ngramme.get(xgramme);
                }
            }

        });
        return LSB[0];
    }

    public long eval_Cissors(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] is_Cissors = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len - 1 == 2) {
                if (MovementBasic.is_Cissors(touches.get(0), touches.get(1))) {
                    is_Cissors[0] += ngramme.get(xgramme);
                }
            }

        });
        return is_Cissors[0];
    }

    public long eval_is_Switch(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] Switch = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len - 1 == 2) {
                if (MovementBasic.is_Switching(touches.get(0), touches.get(1))) {
                    Switch[0] += ngramme.get(xgramme);
                }
            }

        });
        return Switch[0];
    }

    public long eval_Bearing(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] bearing = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 2) {
                if (MovementBasic.is_Bearing(touches.get(0), touches.get(1))) {
                    bearing[0] += ngramme.get(xgramme);
                }
            }

        });
        return bearing[0];
    }

    public long eval_redirection(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] redirec = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 3) {
                if (MovementBasic.is_Redirection(touches.get(0), touches.get(1), touches.get(2))) {
                    redirec[0] += ngramme.get(xgramme);
                }
            }

        });
        return redirec[0];
    }

    public long eval_worst_redirection(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] ret = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 3) {
                if (MovementBasic.worst_Redirection(touches.get(0), touches.get(1), touches.get(2))) {
                    ret[0] += ngramme.get(xgramme);
                }
            }

        });
        return ret[0];
    }

    public long eval_SKS(Map<String, List<Touche>> toucheCorrspond, Map<String, Integer> ngramme) {
        final int[] ret = { 0 };

        toucheCorrspond.forEach((xgramme, touches) -> {
            int len = touches.size();
            if (len == 3) {
                if (MovementBasic.is_SKS(touches.get(0), touches.get(1), touches.get(2))) {
                    ret[0] += ngramme.get(xgramme);
                }
            }

        });
        return ret[0];
    }

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
        CorpusManager.writeMapinCSV(ret, "../resources/EvaluationMapping.csv");
        return ret;

    }

    public static double Total_Score(Map<String, Long> map_eval, int corpus_length) {
        double ret = 0;
        for (Map.Entry<String, Long> entry : map_eval.entrySet()) {
            ret += (entry.getValue() * Score.correspond_score(entry.getKey())) / corpus_length;

        }
        return ret;

    }
}