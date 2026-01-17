package composite;

import strategy.Strategy;

import java.util.HashSet;

public class Et implements Strategy {

    private final HashSet<Strategy> strategies;

    public Et(HashSet<Strategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public boolean isSelected(String word) {
        return strategies.stream().allMatch(s -> s.isSelected(word));
    }
}
