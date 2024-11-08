package domaine;

import java.time.Duration;

public class Instruction {
    private String description;
    private Duration dureeEnMinutes;

    public Instruction(String description, int duree) {
        this.description = description;
        this.dureeEnMinutes = Duration.ofMinutes(duree);
    }

    public String getDescription() {
        return description;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDureeEnMinutes(Duration dureeEnMinutes) {
        this.dureeEnMinutes = dureeEnMinutes;
    }

    @Override
    public String toString() {
        int hours = dureeEnMinutes.toHoursPart();
        int minutes = dureeEnMinutes.toMinutesPart();

        String dureeFormatee = String.format("%02d:%02d", hours, minutes );
        return  description +
                " (" + dureeFormatee + ")";
    }
}

