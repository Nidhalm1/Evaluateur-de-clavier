# Guide d'utilisation du projet

## Organisation des fichiers

### 1. Code source
- Le code du projet est situé dans le dossier `src`.

### 2. Ajout de corpus
- Si vous souhaitez ajouter un corpus, placez un dossier contenant des fichiers `.txt` à évaluer dans :
  `src/main/resources/corpus/`.
- Par défaut, deux corpus sont inclus : 
  - `programmeur`
  - `ecrivain`

### 3. Mise à jour de `corpus.json`
- Après avoir ajouté un ou plusieurs dossiers de corpus, vous devez mettre à jour le fichier `src/main/resources/corpus.json`.
- Pour chaque nouveau dossier ou fichier, ajoutez son chemin dans la liste existante, en les séparant par des virgules.

Exemple :
```json
{
  "corpusFiles": [
    "src/main/resources/corpus/programmeur",
    "src/main/resources/corpus/ecrivain",
    "src/main/resources/corpus/nouveauCorpus"
  ]
}
```

### 4. Modification de la disposition
- Le fichier contenant la disposition des touches est situé dans :
  `src/main/resources/disposition.json`.
- Ce fichier détermine :
  - La position des touches sur le clavier.
  - Le doigt associé à chaque touche.

### 5. Modification du mappage des touches
- Les résultats produits par chaque touche sont définis dans le fichier :
  `src/main/resources/Mapping.json`.
- Si vous souhaitez modifier le résultat produit par une touche (par exemple, que la touche située à la ligne 1, colonne 1, produise `s` au lieu de `a`), modifiez le fichier `Mapping.json` en conséquence.

Exemple :
```json
{
  "k00": "s"
}
```

---

## Compilation et exécution

### 1. Compiler le projet
Depuis la racine du projet, utilisez la commande suivante pour compiler le projet :
```bash
gradle build
```

### 2. Lancer les tests
Pour exécuter les tests, utilisez la commande suivante :
```bash
gradle test
```

---

## Résumé des actions possibles
1. **Ajouter un corpus** : Placez vos fichiers dans `src/main/resources/corpus/` et mettez à jour `corpus.json`.
2. **Modifier la disposition des touches** : Éditez `disposition.json`.
3. **Changer le mappage des touches** : Modifiez `Mapping.json`.
4. **Compiler le projet** : Exécutez `gradle build`.
5. **Lancer les tests** : Exécutez `gradle test`.

N'oubliez pas de recompiler et relancer votre projet après avoir effectué des modifications pour qu'elles soient prises en compte.

