package com.dczajkowski.helpers;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class Helpers {
    @SafeVarargs
    public static <T> T tap(T value, Consumer<T> ...callbacks) {
        Stream.of(callbacks).forEach(callback -> callback.accept(value));

        return value;
    }

    public static void sif(boolean bool, Runnable callback) {
        if (bool) {
            callback.run();
        }
    }
}
