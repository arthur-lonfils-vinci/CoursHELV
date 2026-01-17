package composite;

import strategy.Strategy;

public class Or implements Strategy {

    private final Strategy s1, s2;

    public Or(Strategy s1, Strategy s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public boolean isSelected(String word) {
        return s1.isSelected(word) || s2.isSelected(word);
    }
}
