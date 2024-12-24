package Geometry;
// Un record pour représenter une Touche
public record Touche(String id, int ligne, int colonne, Doigt doigt) {
    public void afficher() {
        System.out.println("Touche: " + id + ", Ligne: " + ligne + ", Colonne: " + colonne + ", Doigt: " + doigt);
    }
}

