package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateWalkToBirdhouseD {

    private final Script script;
    private final Area BIRDHOUSE_D_AREA = new Area(3761, 3755, 3764, 3753);

    public StateWalkToBirdhouseD(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        if (!BIRDHOUSE_D_AREA.contains(script.myPlayer())) {
            script.log("Walking to Birdhouse D...");
            script.getWalking().webWalk(BIRDHOUSE_D_AREA);
            MethodProvider.sleep(600);
        } else {
            script.log("Already at Birdhouse D.");
        }
    }
}
