package domaine;

import util.Util;

import java.time.Duration;

public class Instruction {

    private String instruction;
    private Duration dureeEnMinutes;

    public Instruction(String instruction, int dureeEnMinutes) {
        Util.checkString(instruction);
        Util.checkPositiveOrNul(dureeEnMinutes);
        this.instruction = instruction;
        this.dureeEnMinutes = Duration.ofMinutes(dureeEnMinutes);
    }

    public String getInstruction() {
        return instruction;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    @Override
    public String toString(){
        return instruction + " (" + dureeEnMinutes.toMinutes() + " minutes)";
    }
}
