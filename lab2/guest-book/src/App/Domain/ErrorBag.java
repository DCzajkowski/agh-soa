package App.Domain;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.*;

import static App.Helpers.Helpers.tap;

@SessionScoped
public class ErrorBag implements Serializable {
    private Map<String, List<String>> errors = new HashMap<>();

    public void put(String field, String message) {
        Optional.ofNullable(errors.get(field))
            .orElseGet(() -> tap(new ArrayList<>(), list -> errors.put(field, list)))
            .add(message);
    }

    public boolean hasAny() {
        return errors.size() > 0;
    }

    public boolean hasFor(String field) {
        return errors.containsKey(field);
    }

    public String getFirstFor(String field) {
        return errors.get(field).get(0);
    }

    public void clear() {
        errors.clear();
    }
}
