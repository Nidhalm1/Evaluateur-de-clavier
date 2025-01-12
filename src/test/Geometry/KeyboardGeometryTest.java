package Geometry;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class KeyboardGeometryTest {

    // ...existing code...

    @Test
    void testAttributdoigt() {
        // Arrange
        int colonne = 4;
        Doigt attendu = Doigt.INDEX_GAUCHE;
        
        // Act
        Doigt resultat = Touche.attributdoigt(colonne);
        
        // Assert
        assertEquals(attendu, resultat);
    }

    @Test
    void testAfficher() {
        // Arrange
        Touche touche = new Touche("A", 1, 4, Doigt.INDEX_GAUCHE);
        ByteArrayOutputStream sortie = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(sortie));
        
        try {
            // Act
            touche.afficher();
            
            // Assert
            String attendu = "Touche: A, Ligne: 1, Colonne: 4, Doigt: INDEX_GAUCHE" + System.lineSeparator();
            assertEquals(attendu, sortie.toString());
        } finally {
            // Restaurer le flux de sortie original
            System.setOut(originalOut);
        }
    }

    // ...existing code...
}

