package App.Domain;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SessionScoped
public class OldInput implements Serializable {
    private Map<String, String> oldInput = new HashMap<>();

    public void put(String field, String value) {
        oldInput.put(field, value);
    }

    public void clear() {
        oldInput.clear();
    }

    public String get(String field) {
        return Optional.ofNullable(oldInput.get(field)).orElse("");
    }
}
