package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateWalkToBirdhouseB {

    private final Script script;
    private final Area BIRDHOUSE_B_AREA = new Area(3675, 3882, 3678, 3880);

    public StateWalkToBirdhouseB(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        if (!BIRDHOUSE_B_AREA.contains(script.myPlayer())) {
            script.log("Walking to Birdhouse B...");
            script.getWalking().webWalk(BIRDHOUSE_B_AREA);
            MethodProvider.sleep(600);
        } else {
            script.log("Already at Birdhouse B.");
        }
    }
}
