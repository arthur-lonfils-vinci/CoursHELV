package Q2;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<String> playlist = new ArrayList<>();
    private int currentTrack = 0;
    private PlayerState state;

    public Player() {
        setState(PlayerState.READY);
        for (int i = 1; i <= 12; i++) {
            playlist.add("Track " + i);
        }
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public String getMode() {
        return state.name();
    }

    public String startPlayback() {
        return "Playing " + playlist.get(currentTrack);
    }

    public String nextTrack() {
        currentTrack++;
        if (currentTrack > playlist.size() - 1) {
            currentTrack = 0;
        }
        return "Playing " + playlist.get(currentTrack);
    }

    public String previousTrack() {
        currentTrack--;
        if (currentTrack < 0) {
            currentTrack = playlist.size() - 1;
        }
        return "Playing " + playlist.get(currentTrack);
    }

    public void setCurrentTrackAfterStop() {
        this.currentTrack = 0;
    }

	public String onPlay() {
        return state.onPlay(this);
	}

	public String onLock() {
        return state.onLock(this);
	}

	public String onNext() {
        return state.onNext(this);
	}

	public String onPrevious() {
        return state.onPrevious(this);
	}
}
