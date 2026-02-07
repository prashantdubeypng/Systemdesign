package RobotSimulation;
// here we implementing the compostion so the code show the strategy design
public class Robot {
    private final Flyable flyR;
    private final Talkable talkR;
    private final Walkable walkR;

    public Robot(Flyable flyR, Talkable talkR, Walkable walkR) {
        this.flyR = flyR;
        this.talkR = talkR;
        this.walkR = walkR;
    }

    public String fly() {
        return flyR.Fly();
    }

    public String talk() {
        return talkR.Talk();
    }

    public String walk() {
        return walkR.Walk();
    }

    public static void main(String[] args) {
        Robot robot = new Robot(new NONFly(), new NormalTalk(), new NormalWalk());
        System.out.println(robot.fly());
        System.out.println(robot.talk());
        System.out.println(robot.walk());
    }
}
