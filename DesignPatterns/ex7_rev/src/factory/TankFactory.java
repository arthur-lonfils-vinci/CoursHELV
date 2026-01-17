package factory;

import builder.Robot;
import builder.RobotImpl;
import decorator.MitigationUpgrade;
import decorator.ShieldUpgrade;

public class TankFactory implements RobotFactory {
    @Override
    public Robot createRobot() {
        // Construction complexe avec Builder + Decorators
        Robot bot = new RobotImpl.RobotBuilder()
                .name("Tank")
                .life(200)
                .canon(2)
                .shield(5)
                .freq(200)
                .build();

        bot = new ShieldUpgrade(bot);
        bot = new MitigationUpgrade(bot);
        return bot;
    }
}
