# Guide d'utilisation du projet

## Organisation des fichiers
###  Membres du projet
-MOUSSA Nidhal <br>
-GBAGUIDI Nerval


### 1. Code source
- Le code du projet est situé dans le dossier `src`.
- Par manque de temps, nous n'avons pas pu finaliser l'améliorateur, mais nous disposons d'une base solide.
- Le projet intègre les notions abordées en cours, notamment les streams, la programmation orientée interface, etc.
- Le reste sera détaillé dans les sections suivantes.


### 2. Ajout de corpus
- Si vous souhaitez ajouter un corpus, placez un dossier contenant des fichiers `.txt` à évaluer dans :
  `src/main/resources/corpus/`.
- Par défaut, deux corpus sont inclus : 
  - `programmeur`
  - `ecrivain`

### 3. Mise à jour de `corpus.json`
- Après avoir ajouté un ou plusieurs dossiers de corpus, vous devez mettre à jour le fichier `src/main/resources/corpus.json`.
- Pour chaque nouveau fichier, ajoutez son chemin dans la liste existante, en les séparant par des virgules.

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
- Le fichier `src/main/resources/disposition.json` contient la configuration de la disposition des touches du clavier.
  - Il indique la position de chaque touche et le doigt utilisé pour y accéder.
  - Ce fichier ne précise pas les caractères produits par les touches.

Exemple :
```json
{
  "id": "k00", "ligne": 1, "colonne": 1, "doigt": "AURICULAIRE_GAUCHE"
}
```

### 5. Modification du mappage des touches
- Le fichier `src/main/resources/Mapping.json` définit les caractères réellement produits par chaque touche.
  - Par exemple, si la touche située à la ligne 1, colonne 1 (identifiée comme `k00`) doit produire `a`, cela est défini ici.
  - Vous pouvez modifier le fichier pour personnaliser le comportement des touches.

Exemple :
```json
{
  "k00": "a"
}
```
- Ainsi, vous pouvez modifier la position d'une touche dans `disposition.json` ou son mappage dans `Mapping.json`, selon vos besoins.

### 6. Gestion des séquences complexes
- Pour certaines séquences de caractères comme `îl`, les lettres sont décomposées en plusieurs frappes. Par exemple, `îl` nécessite trois frappes : `^`, `i`, puis `l`.
- Le projet suit également les consignes de mouvements nécessaires, notamment les mouvements comme le "ciseau" et autres gestes ergonomiques, qui sont pris en compte dans le calcul du score.
### 7. Gestion des mouvements
- Pour les mouvements, nous avons respecté le cahier des charges en ajoutant tous les types de mouvements nécessaires. Nous avons notamment ajusté certains mouvements en les minimisant ou en les maximisant, en fonction des poids que nous avons définis. Par exemple, nous avons pris en compte les pourcentages de fatigue des mains etc .
---

## Compilation et exécution

### 1. Compiler le projet
Depuis la racine du projet, utilisez la commande suivante pour compiler le projet :
```bash
gradle build
```
### 2. Executer le projet
```bash
gradle run
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






