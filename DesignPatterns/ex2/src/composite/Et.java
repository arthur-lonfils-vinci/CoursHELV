package composite;

import strategy.SelectionStrategy;

import java.util.List;

public class Et implements SelectionStrategy {
    private final List<SelectionStrategy> strategies;

    public Et(List<SelectionStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public boolean isSelected(String word) {
        for (SelectionStrategy strategy : strategies) {
            if (!strategy.isSelected(word)) {
                return false;
            }
        }
        return true;
    }
}
