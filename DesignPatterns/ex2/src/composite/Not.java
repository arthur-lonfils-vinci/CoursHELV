package composite;

import strategy.SelectionStrategy;

public class Not implements SelectionStrategy {
    private final SelectionStrategy strategy;

    public Not(SelectionStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public boolean isSelected(String word) {
        return !strategy.isSelected(word);
    }
}
