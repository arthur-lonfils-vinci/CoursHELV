package strategy;

public class PalindromeStrategy implements SelectionStrategy {
    @Override
    public boolean isSelected(String word) {
        return word.equals(new StringBuilder(word).reverse().toString());
    }
}