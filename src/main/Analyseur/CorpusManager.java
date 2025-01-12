package Analyseur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Gère le chargement, la lecture et l'analyse d'un corpus.
 */
public class CorpusManager implements CorpusReader {
    private List<String> corpusFiles = new ArrayList<>();

    /**
     * Retourne le contenu du corpus.
     *
     * @return liste du contenu
     */
    public List<String> getContenu() {
        return contenu;
    }

    private List<String> contenu = new ArrayList<>();

    private static String date;

    public CorpusManager() {
        initialiserContenu();
    }

    /**
     * Retourne la liste de fichiers du corpus.
     *
     * @return la liste des chemins de fichiers
     */
    public List<String> getCorpusFiles() {
        return corpusFiles;
    }

    /**
     * Définit la liste de fichiers du corpus.
     *
     * @param corpusFiles la liste à utiliser
     */
    public void setCorpusFiles(List<String> corpusFiles) {
        this.corpusFiles = corpusFiles;
    }

    /**
     * Initialise la liste de fichiers et leur contenu à partir d'un JSON.
     */
    @Override
    public void initialiserContenu() {
        try {
            String fichierJson = "src/main/resources/Corpus.json";
            date = getDateFromJson("src/main/resources/Saving.json");
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(new File(fichierJson),
                    new TypeReference<Map<String, Object>>() {
                    });

            // Récupération des fichiers depuis "corpusFiles"
            Object filesPathObj = map.get("corpusFiles");
            if (filesPathObj instanceof List) {
                List<?> filesPath = (List<?>) filesPathObj;
                this.corpusFiles = new ArrayList<>();
                for (Object pathObj : filesPath) {
                    if (pathObj instanceof String) {
                        this.corpusFiles.add((String) pathObj);
                        String content = Files.readString(Path.of((String) pathObj), StandardCharsets.UTF_8);
                        contenu.add(content); // On ajoute directement le contenu sans altération
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Écrit une map de paires clé-valeur numériques dans un fichier CSV.
     *
     * @param map  la map à écrire
     * @param path le chemin du fichier CSV
     */
    public static void writeMapinCSV(Map<String, ? extends Number> map, String path) {
        try {
            Path filePath = Paths.get(path);
            Path parentDir = filePath.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
                writer.write("Clé,Valeur");
                writer.newLine();

                for (Map.Entry<String, ? extends Number> entry : map.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier CSV : " + e.getMessage());
        }
    }

    /**
     * Lit un fichier CSV et retourne une map clé-valeur (Integer).
     *
     * @param path le chemin du fichier CSV
     * @return la map lue
     */
    public static Map<String, Integer> readMapFromCSV(String path) {
        Map<String, Integer> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                // Ignorer l'en-tête
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Diviser la ligne par la virgule
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    try {
                        Integer value = Integer.parseInt(parts[1].trim());
                        map.put(key, value);
                    } catch (NumberFormatException e) {
                        System.err.println("Valeur non numérique pour la clé : " + key);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
        }

        return map;
    }

    /**
     * Retourne la taille totale du contenu du corpus.
     *
     * @return la somme des longueurs de chaque texte
     */
    public int sizeofcontenu() {
        int ret = 0;
        for (String text : contenu) {
            ret += text.length();

        }

        return ret;
    }

    /**
     * Calcule et retourne un n-gramme (uni, bi et trigramme).
     *
     * @return la map des n-grammes et de leur fréquence
     */
    @Override
    public Map<String, Integer> nGramme() {
        Path path = Paths.get("src/main/resources/nGrammeMap.csv");
        if (date.equalsIgnoreCase(getLastModifiedDate("src/main/resources/Corpus.json")) && Files.exists(path)) {
            return readMapFromCSV(
                    "src/main/resources/nGrammeMap.csv");
        }
        updatedate("src/main/resources/Corpus.json");
        date = getDateFromJson("src/main/resources/Saving.json");
        System.out.println("Le fichier n'existe pas je calcule les donner ");
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
        writeMapinCSV(nGrammeMap, "src/main/resources/nGrammeMap.csv");
        return nGrammeMap;
    }

    /**
     * Met à jour la date de dernière modification dans un fichier JSON.
     *
     * @param jsonFilePath chemin du fichier JSON
     */
    public static void updatedate(String jsonFilePath) {
        Path chemin = Paths.get(jsonFilePath);
        Path saving = Paths.get("src/main/resources/Saving.json");

        try {
            // Obtenir la date de dernière modification du fichier
            FileTime dernierModif = Files.getLastModifiedTime(chemin);
            Instant instant = dernierModif.toInstant();
            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

            // Formater la date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateFormatee = dateTime.format(formatter);

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonNode = (ObjectNode) objectMapper.readTree(Files.newBufferedReader(saving));

            jsonNode.put("date", dateFormatee);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Files.newBufferedWriter(saving), jsonNode);

            System.out.println("La date de dernière modification a été synchronisée : " + dateFormatee);

        } catch (IOException e) {
            System.err.println(
                    "Erreur lors de la synchronisation de la date de dernière modification : " + e.getMessage());
        }
    }

    /**
     * Retourne la date de dernière modification d'un fichier JSON.
     *
     * @param jsonFilePath chemin du fichier JSON
     * @return la date formatée
     */
    public static String getLastModifiedDate(String jsonFilePath) {
        Path chemin = Paths.get(jsonFilePath);
        try {
            // Obtenir la date de dernière modification du fichier
            FileTime dernierModif = Files.getLastModifiedTime(chemin);
            Instant instant = dernierModif.toInstant();
            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

            // Formater la date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return dateTime.format(formatter);

        } catch (IOException e) {
            System.err.println(" Erreur lors de la récupération de la date de modification : " + e.getMessage());
            return null;
        }
    }

    /**
     * Récupère la date stockée dans le fichier Saving.json.
     *
     * @param filePath chemin du fichier JSON
     * @return la date lue
     */
    public static String getDateFromJson(String filePath) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, String> jsonData = objectMapper.readValue(new File(filePath), new TypeReference<Map<String, String>>() {});

            return jsonData.getOrDefault("date", "Valeur non trouvée");
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retourne null en cas d'erreur
        }
    }


}
