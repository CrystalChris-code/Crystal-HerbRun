package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateWalkToBirdhouseC {

    private final Script script;
    private final Area BIRDHOUSE_C_AREA = new Area(3767, 3760, 3770, 3759);

    public StateWalkToBirdhouseC(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        if (!BIRDHOUSE_C_AREA.contains(script.myPlayer())) {
            script.log("Walking to Birdhouse C...");
            script.getWalking().webWalk(BIRDHOUSE_C_AREA);
            MethodProvider.sleep(600);
        } else {
            script.log("Already at Birdhouse C.");
        }
    }
}
