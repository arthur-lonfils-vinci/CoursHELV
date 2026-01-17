import builder.Robot;
import builder.RobotImpl;
import decorator.CanonUpgrade;
import decorator.MitigationUpgrade;
import decorator.ShieldUpgrade;
import factory.PicVertFactory;
import factory.RobotFactory;
import factory.TankFactory;

public class PatternRobots {

    public static void fight(Robot robot1, Robot robot2) {
        int tick1 = robot1.getFreq();
        int tick2 = robot2.getFreq();
        while (robot2.diffLife(0) > 0 && robot1.diffLife(0) > 0) {
            int tick = Math.min(tick1, tick2);
            tick1 -= tick;
            tick2 -= tick;
            if (tick1 == 0) {// robot 1 feu
                tick1 = shoot(robot1, robot2);
            }
            if (tick2 == 0) {// robot 2 feu
                tick2 = shoot(robot2, robot1);
            }
        }
    }

    private static int shoot(Robot robot1, Robot robot2) {
        int dmg = Math.max(0, robot1.getCanon() - robot2.getShield());
        int lost = robot2.diffLife(0) - robot2.diffLife(-dmg);
        System.out.println(robot1.getName() + " shoots for " + lost);
        if (robot2.diffLife(0) <= 0) {
            System.out.println("Kabooom " + robot2.getName());
        }
        return robot1.getFreq();
    }

    public static void main(String[] args) {
        RobotFactory picVertFactory = new PicVertFactory();
        RobotFactory tankFactory = new TankFactory();


        System.out.println("*************  First Fight -- Classic  *************");
        Robot robot1 = new RobotImpl.RobotBuilder()
                .name("Robot1")
                .canon(10)
                .shield(2)
                .freq(100)
                .build();
        robot1 = new CanonUpgrade(robot1);


        Robot robot2 = new RobotImpl.RobotBuilder()
                .name("Robot2")
                .canon(9)
                .shield(3)
                .freq(90)
                .build();
        robot2 = new ShieldUpgrade(robot2);
        robot2 = new MitigationUpgrade(robot2);

        System.out.println("Combat entre " + robot1.getName() + " et " + robot2.getName());
        System.out.println("---------");

        fight(robot1, robot2);


        System.out.println("\n\n*************  Second Fight -- Pre-Configured  *************");

        Robot picVert = picVertFactory.createRobot();
        Robot tank = tankFactory.createRobot();

        System.out.println("Combat entre " + picVert.getName() + " et " + tank.getName());
        System.out.println("---------");

        fight(picVert, tank);

    }
}
