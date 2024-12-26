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

    public Map<String, String> reverseBase() {
        Map<String, String> reverseMappage = new HashMap<>();
        for (Couche couche : couches) {
            if ("base".equalsIgnoreCase(couche.nom())) {
                couche.mappage().forEach((key, value) -> reverseMappage.put(value, key));
            }
        }
        return reverseMappage;
    }

    public Map<String, String> reverse_DEAD_ACUTE() {
        Map<String, String> reverseMappage = new HashMap<>();
        for (Couche couche : couches) {
            if ("DEAD_ACUTE".equalsIgnoreCase(couche.nom())) {
                couche.mappage().forEach((key, value) -> reverseMappage.put(value, key));
            }
        }
        return reverseMappage;
    }

    public Map<String, String> reverse_DEAD_GRAVE() {
        Map<String, String> reverseMappage = new HashMap<>();
        for (Couche couche : couches) {
            if ("DEAD_GRAVE".equalsIgnoreCase(couche.nom())) {
                couche.mappage().forEach((key, value) -> reverseMappage.put(value, key));
            }
        }
        return reverseMappage;
    }

    public Map<String, String> reverse_DEAD_CIRCUMFLEX() {
        Map<String, String> reverseMappage = new HashMap<>();
        for (Couche couche : couches) {
            if ("DEAD_CIRCUMFLEX".equalsIgnoreCase(couche.nom())) {
                couche.mappage().forEach((key, value) -> reverseMappage.put(value, key));
            }
        }
        return reverseMappage;
    }

    public Map<String, String> reverse_DEAD_TREMA() {
        Map<String, String> reverseMappage = new HashMap<>();
        for (Couche couche : couches) {
            if ("DEAD_TREMA".equalsIgnoreCase(couche.nom())) {
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

    public int mapCorrespondant(List<Map<String, String>> list, String charactere) {
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> mapActuel = list.get(i);
            if (mapActuel.containsKey(charactere)) { // Vérifier si la Map contient la clé
                return i; // Retourner l'indice et la valeur
            }
        }
        return -1;
    }

    public Map<String, List<Touche>> toucheCorrespondant(Map<String, Integer> nGrammeMap, KeyboardGeometry clavier) {
        List<Map<String, String>> reverseMaps = List.of(
                reverseBase(),
                reverse_DEAD_ACUTE(),
                reverse_DEAD_GRAVE(),
                reverse_DEAD_CIRCUMFLEX(),
                reverse_DEAD_TREMA());

        Map<String, List<Touche>> result = new HashMap<>();

        nGrammeMap.forEach((word, count) -> {
            List<Touche> touches = new ArrayList<>();
            boolean majuscule = false;
            for (char c : word.toCharArray()) {
                if (Character.isUpperCase(c) && majuscule == false) {
                    String toucheId = reverseMaps.get(0).get("shift");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                    majuscule = true;
                }
                if (Character.isLowerCase(c) && majuscule == true) {
                    String toucheId = reverseMaps.get(0).get("shift");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                    majuscule = false;
                }
                String ch = String.valueOf(Character.toLowerCase(c));
                int indiceMap = mapCorrespondant(reverseMaps, ch);
                if (indiceMap == 1) {
                    String toucheId = reverseMaps.get(0).get("dead_acute");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                }
                else if (indiceMap == 2) {
                    String toucheId = reverseMaps.get(0).get("dead_grave");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                }
                else if (indiceMap == 3) {
                    String toucheId = reverseMaps.get(0).get("dead_circumflex");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                }
                else if (indiceMap == 4) {
                    String toucheId = reverseMaps.get(0).get("dead_trema");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                }
                String toucheId = reverseMaps.get(indiceMap).get(ch);
                if (toucheId != null) {
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                }
            }
            if (touches.size()<4){
                result.put(word, touches);
            }
        });

        return result;
    }

    public static void main(String[] args) {
        KeyboardLayout layout = initialiserContenu();
        if (layout != null) {
            Map<String, Integer> nGrammeMap = new HashMap<>();
            nGrammeMap.put("bO", 1); // Données de test
            KeyboardGeometry clavier = KeyboardGeometry.initialiserAttribut(); // Vérifiez cette méthode
            Map<String, List<Touche>> result = layout.toucheCorrespondant(nGrammeMap, clavier);
            System.out.println("Result: " + result);
            System.out.println("Nombre de touches: " + result.values().stream().mapToInt(List::size).sum());
        }
    }

}
