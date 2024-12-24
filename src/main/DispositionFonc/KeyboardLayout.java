package DispositionFonc;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import Geometry.KeyboardGeometry;
import Geometry.Touche;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

// KeyboardLayout est un ensemble de couches
public record KeyboardLayout(List<Couche> couches) {

    public Map<String, String> reverseMappage() {
        Map<String, String> reverseMappage = new HashMap<>();
        for (Couche couche : couches) {
            if ("base".equalsIgnoreCase(couche.nom())) {
                couche.mappage().forEach((key, value) -> reverseMappage.put(value, key));
            }
        }
        return reverseMappage;
    }

    public static KeyboardLayout initialiserContenu() {
        try {
            String fichierJson = "src/main/resources/Mapping.json";
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(fichierJson), KeyboardLayout.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return a default KeyboardLayout object
        }
    }

    public Map<String, List<Touche>> toucheCorrespondant(Map<String, Integer> nGrammeMap, KeyboardGeometry clavier) {
        Map<String, String> reverseMap = reverseMappage();
        Map<String, List<Touche>> result = new HashMap<>();

        nGrammeMap.forEach((word, count) -> {
            List<Touche> touches = new ArrayList<>();
            boolean majuscule = false;
            for (char c : word.toCharArray()) {
                if (Character.isUpperCase(c) && majuscule == false) {
                    clavier.findToucheById("KSHIFT").ifPresent(touche -> {
                        touches.add(touche);
                    });
                    majuscule = true;
                }
                if (Character.isLowerCase(c) && majuscule == true) {
                    clavier.findToucheById("KSHIFT").ifPresent(touche -> {
                        touches.add(touche);
                    });
                    majuscule = false;
                }
                String ch = String.valueOf(Character.toLowerCase(c));
                String toucheId = reverseMap.get(ch);
                if (toucheId != null) {
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                }
            }
            result.put(word, touches);
        });

        return result;
    }

    public static void main(String[] args) {
        KeyboardLayout layout = initialiserContenu();
        if (layout != null) {
            Map<String, Integer> nGrammeMap = new HashMap<>();
            nGrammeMap.put("bOn", 1); // Données de test
            KeyboardGeometry clavier = KeyboardGeometry.initialiserAttribut(); // Vérifiez cette méthode
            Map<String, List<Touche>> result = layout.toucheCorrespondant(nGrammeMap, clavier);
            System.out.println("Result: " + result);
        }
    }

}
