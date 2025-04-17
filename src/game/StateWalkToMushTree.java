package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateWalkToMushTree {

    private final Script script;
    private final Area BIRDHOUSE_MUSH_AREA = new Area(3676, 3870, 3679, 3868);

    public StateWalkToMushTree(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        if (!BIRDHOUSE_MUSH_AREA.contains(script.myPlayer())) {
            script.log("Walking to MushTree...");
            script.getWalking().webWalk(BIRDHOUSE_MUSH_AREA);
            MethodProvider.sleep(600);
        } else {
            script.log("Already at MushTree.");
        }
    }
}
