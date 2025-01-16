package Analyseur;

import java.nio.file.Path;

public class DateUpdater implements CorpusObserver {
    @Override
    public void onCorpusModified(Path corpusPath) {
        CorpusManager.updatedate(corpusPath.toString());
    }
}
