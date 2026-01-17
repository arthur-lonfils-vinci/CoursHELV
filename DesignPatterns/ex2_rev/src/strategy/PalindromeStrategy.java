package strategy;

public class PalindromeStrategy implements Strategy {
    @Override
    public boolean isSelected(String word) {
        return word.contentEquals(new StringBuilder(word).reverse().toString());
    }
}
