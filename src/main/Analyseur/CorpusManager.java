package Analyseur;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class CorpusManager implements CorpusReader {
    private List<String> corpusFiles = new ArrayList<>();
    private List<String> contenu = new ArrayList<>();

    public CorpusManager() {
        initialiserContenu();
    }

    public List<String> getCorpusFiles() {
        return corpusFiles;
    }

    public void setCorpusFiles(List<String> corpusFiles) {
        this.corpusFiles = corpusFiles;
    }

    @Override
    public void initialiserContenu() {
        try {
            String fichierJson = "src/main/resources/Corpus.json";
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, List<String>> map = objectMapper.readValue(new File(fichierJson), new TypeReference<Map<String, List<String>>>(){});
            List<String> filesPath = map.get("corpusFiles");
            this.corpusFiles = List.copyOf(filesPath);
            for (String path : filesPath) {
                String content = Files.readString(Path.of(path), StandardCharsets.UTF_8); // Spécifier l'encodage
                content = content.replaceAll("[^\\p{Print}]", ""); // Normaliser le texte
                contenu.add(content);
            }
            for (String content : contenu) {
                System.out.println(content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Map<String, Integer> nGramme() {
        Map<String, Integer> nGrammeMap = new HashMap<>();
        for (String texte : contenu) {
            int len = texte.length();
            for (int i = 0; i < len; i++) {
                // Génération des unigrammes
                String unigramme = texte.substring(i, i + 1);
                nGrammeMap.put(unigramme, nGrammeMap.getOrDefault(unigramme, 0) + 1);
                // Génération des bigrammes
                if (i + 2 <= len) {
                    String bigramme = texte.substring(i, i + 2);
                    nGrammeMap.put(bigramme, nGrammeMap.getOrDefault(bigramme, 0) + 1);
                }
                
                // Génération des trigrammes
                if (i + 3 <= len) {
                    String trigramme = texte.substring(i, i + 3);
                    nGrammeMap.put(trigramme, nGrammeMap.getOrDefault(trigramme, 0) + 1);
                }
            }
        }
        return nGrammeMap;
    }

    public static void main(String[] args) {
        CorpusManager corpusManager = new CorpusManager();
        Map<String, Integer> unGramme =  corpusManager.nGramme();
        unGramme.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
