package App.Domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BeerExpert {
    private final static Map<BeerColor, String> beers;

    static {
        HashMap<BeerColor, String> _beers = new HashMap<>();
        _beers.put(BeerColor.YELLOW, "Budweiser");
        _beers.put(BeerColor.AMBER, "Bishops Finger");
        _beers.put(BeerColor.BROWN, "Cerna Hora");
        _beers.put(BeerColor.BLACK, "Komes Porter");

        beers = Collections.unmodifiableMap(_beers);
    }

    public String getBeerProposition(BeerColor color) {
        return beers.get(color);
    }
}
