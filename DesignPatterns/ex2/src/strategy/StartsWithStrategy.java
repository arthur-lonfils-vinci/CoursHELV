package strategy;

public class StartsWithStrategy implements SelectionStrategy {
    private final String prefix;

    public StartsWithStrategy(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean isSelected(String word) {
        return word.startsWith(prefix);
    }
}