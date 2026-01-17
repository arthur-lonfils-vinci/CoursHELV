package decorator;

import builder.Robot;

public class ShieldUpgrade extends RobotDecorator {
    public ShieldUpgrade(Robot robot) { super(robot); }
    @Override
    public int getShield() { return decoratedRobot.getShield() * 2; }
}
