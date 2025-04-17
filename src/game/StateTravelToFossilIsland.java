package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateTravelToFossilIsland {

    private final Script script;

    // Area in front of the boat to Fossil Island (Digsite)
    private final Area BOAT_AREA = new Area(3360, 3448, 3363, 3445);

    public StateTravelToFossilIsland(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        if (!BOAT_AREA.contains(script.myPlayer())) {
            script.log("Walking to the boat at Digsite...");
            script.getWalking().webWalk(BOAT_AREA);
            MethodProvider.sleep(800);
        }

        // Once in area, talk to the Barge guard
        NPC bargeGuard = script.getNpcs().closest("Barge guard");

        if (bargeGuard != null && bargeGuard.isVisible()) {
            script.log("Talking to Barge guard to travel...");
            if (bargeGuard.interact("Quick-Travel")) {
                MethodProvider.sleep(2000); // Give time for travel animation/dialogue
            }
        } else {
            script.log("Couldn't find Barge guard.");
        }

        MethodProvider.sleep(3000);
    }
}
