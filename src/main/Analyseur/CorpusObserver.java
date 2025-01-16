package Analyseur;

import java.nio.file.Path;

public interface CorpusObserver {
    void onCorpusModified(Path corpusPath);
}

