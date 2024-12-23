package DispositionFonc;

import java.util.List;
import java.util.Map;

import Geometry.KeyboardGeometry;
import Geometry.Touche;

import java.util.ArrayList;
import java.util.HashMap;

// KeyboardLayout est un ensemble de couches
public record KeyboardLayout(List<Couche> couches) {

    public Map<String, String> reverseMappage() {
        Map<String, String> reverseMappage = new HashMap<>();
        for (Couche couche : couches) {
            if ("base".equalsIgnoreCase(couche.nom())) {
                couche.mappage().forEach((key, value) -> reverseMappage.put(value, key));
                return reverseMappage;
            }
        }
        return reverseMappage;
    }

    public Map<String, List<Touche>> toucheCorrespondant(Map<String, Integer> nGrammeMap, KeyboardGeometry clavier) {
        Map<String, String> reverseMappage = reverseMappage();
        Map<String, List<Touche>> toucheCorrespondant = new HashMap<String, List<Touche>>();
        nGrammeMap.forEach((key, value) -> {
            boolean majuscule = false;
            int len = key.length();
            List<Touche> listetouches = new ArrayList<Touche>();
            for (int i = 0; i < len; i++) {
                if (Character.isUpperCase(key.charAt(i)) && majuscule == false) {
                    majuscule = true;
                    String idTouche = reverseMappage.get("");
                    clavier.findToucheById(idTouche).ifPresent(listetouches::add);
                } else if (Character.isLowerCase(key.charAt(i)) && majuscule) {
                    majuscule = false;
                    String idTouche = reverseMappage.get("");
                    clavier.findToucheById(idTouche).ifPresent(listetouches::add);
                }
                String charctere = String.valueOf(Character.toLowerCase(key.charAt(i)));
                String idTouche = reverseMappage.get(charctere);
                clavier.findToucheById(idTouche).ifPresent(listetouches::add);
            }
            toucheCorrespondant.put(key, listetouches);
        });
        return toucheCorrespondant;
    }
}
