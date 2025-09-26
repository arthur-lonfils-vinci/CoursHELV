package decorator;

import strategy.SelectionStrategy;

public class Compteur implements SelectionStrategy {
    private final SelectionStrategy strategy;
    private int count = 0;

    public Compteur(SelectionStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public boolean isSelected(String word) {
        boolean selected = strategy.isSelected(word);
        if (selected) count++;
        return selected;
    }

    public int getCount() {
        return count;
    }
}
