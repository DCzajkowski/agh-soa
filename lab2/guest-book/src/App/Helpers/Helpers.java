package App.Helpers;

import java.util.function.Consumer;

public class Helpers {
    public static <T> T tap(T value, Consumer<T> callback) {
        callback.accept(value);

        return value;
    }
}
