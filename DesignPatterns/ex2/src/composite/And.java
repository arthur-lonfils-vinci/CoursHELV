package composite;

import strategy.SelectionStrategy;

public class And implements SelectionStrategy {
    private final SelectionStrategy s1, s2;

    public And(SelectionStrategy s1, SelectionStrategy s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public boolean isSelected(String word) {
        return s1.isSelected(word) && s2.isSelected(word);
    }
}