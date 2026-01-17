package decorator;

import builder.Robot;

abstract class RobotDecorator implements Robot {
    protected final Robot decoratedRobot;

    public RobotDecorator(Robot robot) {
        this.decoratedRobot = robot;
    }

    public int getCanon() { return decoratedRobot.getCanon(); }
    public int getShield() { return decoratedRobot.getShield(); }
    public int getFreq() { return decoratedRobot.getFreq(); }
    public String getName() { return decoratedRobot.getName(); }
    public int diffLife(int i) { return decoratedRobot.diffLife(i); }
}
