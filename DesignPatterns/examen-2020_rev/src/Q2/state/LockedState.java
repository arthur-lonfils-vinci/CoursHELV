package Q2.state;

import Q2.Player;

public class LockedState extends State{

    @Override
    public String onPrevious() {
        return "Locked...";
    }

    @Override
    public String onNext() {
        return "Locked...";
    }

    @Override
    public String onLock() {
        return "Locked...";
    }

    @Override
    public String onPlay() {
        player.changeState(new ReadyState(player));
        return "Ready";
    }

    public LockedState(Player player) {
        super(player);
    }
}
