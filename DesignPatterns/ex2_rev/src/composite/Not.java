package composite;

import strategy.Strategy;

public class Not implements Strategy {

    private final Strategy strategy;

    public Not(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public boolean isSelected(String word) {
        return !strategy.isSelected(word);
    }
}
