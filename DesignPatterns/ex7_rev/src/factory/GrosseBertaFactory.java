package factory;

import builder.Robot;
import builder.RobotImpl;

public class GrosseBertaFactory implements RobotFactory {
    @Override
    public Robot createRobot() {
        return new RobotImpl.RobotBuilder()
                .name("Grosse Berta")
                .canon(20)
                .shield(1)
                .freq(300)
                .build();
    }
}
