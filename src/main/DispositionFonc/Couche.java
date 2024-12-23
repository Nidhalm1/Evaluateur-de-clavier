package DispositionFonc;
import java.util.HashMap;
import java.util.Map;


// Couche peut être un record, immuable, qui associe un nom à un mappage
public record Couche(String nom, Map<String, String> mappage) {

    public Map<String, String> reverseMap() {
        Map<String, String> reverseMap = new HashMap<>();
        mappage.forEach((key, value) -> reverseMap.put(value, key));
        return reverseMap;
    }

}
