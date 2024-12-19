import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class PizzaComposable extends Pizza{
    private Client createur;
    private LocalDateTime date;

    public PizzaComposable(Client createur) {
        super("Pizza composable du client " + createur.getNumero(), "Pizza de " + createur.getPrenom() + " " + createur.getNom());
        Util.checkObject(createur);
        this.createur = createur;
        this.date = LocalDateTime.now();
    }

    public Client getCreateur() {
        return this.createur;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return super.toString() + "\nPizza créée le " + formater.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PizzaComposable that = (PizzaComposable) o;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + date.hashCode();
    }
}
