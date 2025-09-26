package strategy;

public class LengthStrategy implements SelectionStrategy {
    private final int length;

    public LengthStrategy(int length) {
        this.length = length;
    }

    @Override
    public boolean isSelected(String word) {
        return word.length() == length;
    }
}