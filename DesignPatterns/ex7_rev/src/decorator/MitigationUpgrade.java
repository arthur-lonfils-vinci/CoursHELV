package decorator;

import builder.Robot;

public class MitigationUpgrade extends RobotDecorator {
    public MitigationUpgrade(Robot robot) { super(robot); }
    @Override
    public int diffLife(int i) {
        if (i < 0) {
            i = i / 2;
        }
        return decoratedRobot.diffLife(i);
    }
}
