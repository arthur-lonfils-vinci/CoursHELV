package decorator;

import builder.Robot;

public class CanonUpgrade extends RobotDecorator {
    public CanonUpgrade(Robot robot) { super(robot); }
    @Override
    public int getCanon() { return decoratedRobot.getCanon() * 2; }
}
