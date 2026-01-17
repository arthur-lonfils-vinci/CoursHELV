package factory;

import builder.Robot;
import builder.RobotImpl;

public class PicVertFactory implements RobotFactory {
    @Override
    public Robot createRobot() {
        return new RobotImpl.RobotBuilder()
                .name("Pic-Vert")
                .canon(2)
                .shield(0)
                .freq(20)
                .build();
    }
}
