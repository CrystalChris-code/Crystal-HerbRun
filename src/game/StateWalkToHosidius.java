package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateWalkToHosidius {

    private final Script script;

    public StateWalkToHosidius(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
            Area HosidiusPatch = new Area(1735, 3553, 1741, 3548);
            script.log("Walking to Hosidius herb patch...");
            script.getWalking().webWalk(HosidiusPatch);
            MethodProvider.sleep(2500);
        }
}
