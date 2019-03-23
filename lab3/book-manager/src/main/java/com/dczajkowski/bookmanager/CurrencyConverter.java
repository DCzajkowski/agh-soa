package com.dczajkowski.bookmanager;

import java.util.HashMap;
import java.util.Map;

import static com.dczajkowski.helpers.Helpers.tap;

public class CurrencyConverter {
    private final Currency fromCurrency;
    private final int cost;
    private final Map<Currency, Double> rates = tap(new HashMap<>(), map -> {
        map.put(Currency.PLN, 1.);
        map.put(Currency.EUR, .23);
    });

    public CurrencyConverter(int cost, Currency fromCurrency) {
        this.cost = cost;
        this.fromCurrency = fromCurrency;
    }

    public int to(Currency toCurrency) {
        return (int) (this.cost / rates.get(fromCurrency) * rates.get(toCurrency));
    }
}
