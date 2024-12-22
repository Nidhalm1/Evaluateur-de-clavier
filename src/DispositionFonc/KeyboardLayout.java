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
            couche.mappage().forEach((key, value) -> reverseMappage.put(value, key));
        }
        return reverseMappage;
    }

    public Map<String,List<Touche>> toucheCorrespondant(Map<String, Integer> nGrammeMap , KeyboardGeometry clavier){
        Map<String, String> reverseMappage = reverseMappage();
        Map<String,List<Touche>> toucheCorrespondant = new HashMap<String, List<Touche>>() ;
        nGrammeMap.forEach((key, value) -> {
            int len = key.length();
            List<Touche> listetouches = new ArrayList<>();
            for (int i = 0; i <len; i++) {
                String charctere = String.valueOf(key.charAt(i));
                String idTouche = reverseMappage.get(charctere);
                clavier.findToucheById(idTouche).ifPresent(listetouches::add);
            }
            toucheCorrespondant.put(key, listetouches);
        });
        return toucheCorrespondant;
    }
}
