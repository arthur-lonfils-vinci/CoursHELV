package composite;

import strategy.SelectionStrategy;

import java.util.List;

public class Ou implements SelectionStrategy {
    private final List<SelectionStrategy> strategies;

    public Ou(List<SelectionStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public boolean isSelected(String word) {
        for (SelectionStrategy strategy : strategies) {
            if (strategy.isSelected(word)) {
                return true;
            }
        }
        return false;
    }

}
