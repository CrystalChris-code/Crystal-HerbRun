package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateWalkToBirdhouseA {

    private final Script script;
    private final Area BIRDHOUSE_A_AREA = new Area(3678, 3815, 3681, 3813);

    public StateWalkToBirdhouseA(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        if (!BIRDHOUSE_A_AREA.contains(script.myPlayer())) {
            script.log("Walking to Birdhouse A...");
            script.getWalking().webWalk(BIRDHOUSE_A_AREA);
            MethodProvider.sleep(600);
        } else {
            script.log("Already at Birdhouse A.");
        }
    }
}
