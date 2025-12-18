package Q2;

public enum PlayerState {
    READY {
        public String onPlay(Player player) {
            String action = player.startPlayback();
            player.setState(PlayerState.PLAYING);
            return action;
        }

        public String onLock(Player player) {
            player.setState(PlayerState.LOCKED);
            return "Locked...";
        }

        public String onNext(Player player) {
            return "Locked...";
        }

        public String onPrevious(Player player) {
            return "Locked...";
        }
    },
    LOCKED {
        public String onPlay(Player player) {
            player.setState(PlayerState.READY);
            return "Ready";
        }

        public String onLock(Player player) {
            return "Locked...";
        }

        public String onNext(Player player) {
            return "Locked...";
        }

        public String onPrevious(Player player) {
            return "Locked...";
        }
    },
    PLAYING {
        public String onPlay(Player player) {
            player.setState(PlayerState.READY);
            return "Paused...";
        }

        public String onLock(Player player) {
            player.setState(PlayerState.LOCKED);
            player.setCurrentTrackAfterStop();
            return "Stop playing";
        }

        public String onNext(Player player) {
            return player.nextTrack();
        }

        public String onPrevious(Player player) {
            return player.previousTrack();
        }
    };


    public String onPlay(Player player) {
        throw new RuntimeException();
    }

    public String onLock(Player player) {
        throw new RuntimeException();
    }

    public abstract String onNext(Player player);

    public abstract String onPrevious(Player player);
}
