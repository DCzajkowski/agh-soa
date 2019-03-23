package com.dczajkowski.randomnumber;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import java.util.HashMap;
import java.util.Map;

import static com.dczajkowski.helpers.Helpers.sif;
import static com.dczajkowski.helpers.Helpers.tap;

@Named("NumberGenerator")
@ApplicationScoped
public class NumberGenerator {
    private static int number = (int) (Math.random() * 5 + 1);
    private static Map<Integer, Integer> viewCounter = new HashMap<>();

    public String send(Integer i) {
        return tap(i == number ? "won" : String.valueOf(i), b -> sif(b.equals("won"), this::generate), b -> incrementViewCount(i));
    }

    private void generate() {
        number = (int) (Math.random() * 5 + 1);
    }

    private void incrementViewCount(Integer i) {
        viewCounter.put(i, viewCounter.getOrDefault(i, 0) + 1);
    }

    public int getViewCountFor(Integer i) {
        return viewCounter.get(i);
    }

    public Map<Integer, Integer> getViewCounter() {
        return viewCounter;
    }

    public void setViewCounter(Map<Integer, Integer> viewCounter) {
        NumberGenerator.viewCounter = viewCounter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        NumberGenerator.number = number;
    }
}
