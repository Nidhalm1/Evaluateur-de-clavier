package DispositionFonc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import Geometry.Doigt;
import Geometry.KeyboardGeometry;
import Geometry.Touche;

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

    public Map<String, String> reverse_shift() {
        Map<String, String> reverseMappage = new HashMap<>();
        for (Couche couche : couches) {
            if ("shift_pressed".equalsIgnoreCase(couche.nom())) {
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
                reverse_DEAD_TREMA(),
                reverse_shift());

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
                } else if (indiceMap == 2) {
                    String toucheId = reverseMaps.get(0).get("dead_grave");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                } else if (indiceMap == 3) {
                    String toucheId = reverseMaps.get(0).get("dead_circumflex");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                } else if (indiceMap == 4) {
                    String toucheId = reverseMaps.get(0).get("dead_trema");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                } else if (indiceMap == 5) {
                    String toucheId = reverseMaps.get(0).get("shift");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                }
                if (indiceMap >= 0 && indiceMap < reverseMaps.size()) {
                    String toucheId = reverseMaps.get(indiceMap).get(ch);
                    if (toucheId != null) {
                        clavier.findToucheById(toucheId).ifPresent(touche -> {
                            touches.add(touche);
                        });
                    }}
                if (c=='\n'){
                    String toucheId = reverseMaps.get(0).get("enter");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                }
                if (c==' '){
                    String toucheId = reverseMaps.get(0).get("space");
                    clavier.findToucheById(toucheId).ifPresent(touche -> {
                        touches.add(touche);
                    });
                }
            }
            if (touches.size() < 4) {
                result.put(word, touches);
            }
        });

        return result;
    }

   public void create_disposition(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);
      
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(""); 
        }

        Map<String, List<Touche>> newContent = new HashMap<>();
        newContent.put("touches",new ArrayList<Touche>());

        Map<String, String> reverseMap = 
            reverseBase();

            reverseMap=shuffleReverseMap(reverseMap);
        
        List<String> keys_yet_attribued=new ArrayList<>();
        int line =1;
        int column=1;
        int line_specific=0;
        int column_specific=0;

        List<String> specificKeys = List.of("@","&","é","\"","'","(","§","è","!","ç","à",")","-","shift","enter","space");

        for(String key_s:specificKeys){
            if(!keys_yet_attribued.contains(key_s)){
                if (key_s.equalsIgnoreCase("shift")){
                    newContent.get("touches").add(new Touche(reverseMap.get(key_s),3,0,Touche.attributdoigt(0)));
                    keys_yet_attribued.add(key_s);
                    continue;
                    
                }
                if (key_s.equalsIgnoreCase("enter")){
                    newContent.get("touches").add(new Touche(reverseMap.get(key_s),1,13,Touche.attributdoigt(13)));
                    keys_yet_attribued.add(key_s);
                    continue;
                }
                if (key_s.equalsIgnoreCase("space")){
                   
                    newContent.get("touches").add(new Touche(reverseMap.get(key_s),4,4,Doigt.POUCE_DROIT));
                   
                    keys_yet_attribued.add(key_s);
                    continue;
                } 
                newContent.get("touches").add(new Touche(reverseMap.get(key_s),line_specific,column_specific,Touche.attributdoigt(column_specific)));
                keys_yet_attribued.add(key_s);
                column_specific++;
           }
        }

        for (Map.Entry<String, String> en : reverseMap.entrySet()) {
            String key = en.getKey();
            if (!keys_yet_attribued.contains(key)){
                newContent.get("touches").add(new Touche(en.getValue(), line, column, Touche.attributdoigt(column)));
                keys_yet_attribued.add(key);
                if (column==12){
                    column=1;
                    line++;
                }else{
                    column++;
                }
            }
        }
        mapper.writeValue(file, newContent);
    }

  

public Map<String, String> shuffleReverseMap(Map<String, String> reverseMap) {
   
    List<Map.Entry<String, String>> entryList = new ArrayList<>(reverseMap.entrySet());
    Collections.shuffle(entryList);

   
    Map<String, String> shuffledMap = new LinkedHashMap<>();
    for (Map.Entry<String, String> entry : entryList) {
        shuffledMap.put(entry.getKey(), entry.getValue());
    }

    return shuffledMap;
}

   
}
