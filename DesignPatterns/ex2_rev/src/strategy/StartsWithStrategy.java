package strategy;

public class StartsWithStrategy implements Strategy {

    private String letter;

    public StartsWithStrategy(String letter) {
        this.letter = letter;
    }

    @Override
    public boolean isSelected(String word) {
        return word.startsWith(letter);
    }
}
