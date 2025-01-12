package Analyseur;

import java.util.Map;

/**
 * Interface décrivant la lecture d'un corpus.
 */
public interface CorpusReader {

    /**
     * Initialise le contenu du corpus.
     */
    public void initialiserContenu ();

    /**
     * Retourne un n-gramme basé sur le contenu chargé.
     * @return Map contenant le n-gramme et sa fréquence
     */
    public Map<String, Integer> nGramme();
}
