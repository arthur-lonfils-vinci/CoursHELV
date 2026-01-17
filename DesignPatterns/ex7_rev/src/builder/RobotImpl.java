package builder;

public class RobotImpl implements Robot {

    private final String name;
    private final int canon;
    private final int shield;
    private final int freq;
    private int life;

    private RobotImpl(RobotBuilder builder) {
        this.name = builder.name;
        this.canon = builder.canon;
        this.shield = builder.shield;
        this.freq = builder.freq;
        this.life = builder.life;
    }

    @Override
    public int getCanon() {
        return canon;
    }

    @Override
    public int getShield() {
        return shield;
    }

    @Override
    public int getFreq() {
        return freq;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int diffLife(int i) {
        if (i == 0) {
            return this.life;
        }
        this.life += i;
        return this.life;
    }

    public static class RobotBuilder {
        private String name = "Robot";
        private int life = 100;
        private int canon = 1;
        private int shield = 1;
        private int freq = 100;

        public RobotBuilder() {
        }

        public RobotBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RobotBuilder life(int life) {
            this.life = life;
            return this;
        }

        public RobotBuilder canon(int canon) {
            this.canon = canon;
            return this;
        }

        public RobotBuilder shield(int shield) {
            this.shield = shield;
            return this;
        }

        public RobotBuilder freq(int freq) {
            this.freq = freq;
            return this;
        }

        public RobotImpl build() {
            return new RobotImpl(this);
        }
    }
}