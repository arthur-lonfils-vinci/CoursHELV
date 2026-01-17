package decorator;

import strategy.Strategy;

public class Counter implements Strategy {

    private final Strategy strategy;
    private int count = 0;

    public Counter(Strategy strategy) {
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
