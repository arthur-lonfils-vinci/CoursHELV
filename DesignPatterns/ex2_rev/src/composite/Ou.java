package composite;

import strategy.Strategy;

import java.util.HashSet;

public class Ou implements Strategy {

    private final HashSet<Strategy> strategies;

    public Ou(HashSet<Strategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public boolean isSelected(String word) {
        return strategies.stream().anyMatch(s -> s.isSelected(word));
    }
}
