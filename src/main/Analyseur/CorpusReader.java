package Analyseur;

import java.util.Map;

public interface CorpusReader {
    public void initialiserContenu ();
    public Map<String, Integer> nGramme();
}
