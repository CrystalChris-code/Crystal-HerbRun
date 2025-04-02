package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateTeleportToPhasmatys {

    private final Script script;

    public StateTeleportToPhasmatys(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        String teleportItemName = "Fenkenstrain's castle teleport";
        if (script.getInventory().contains(teleportItemName)) {
            script.log("Using " + teleportItemName);
            script.getInventory().getItem(teleportItemName).interact("Break");

            MethodProvider.sleep(5000);

            Area PhasmatysPatch = new Area(3602, 3533, 3608, 3527);
            script.log("Walking to Phasmatys herb patch...");
            script.getWalking().webWalk(PhasmatysPatch);
            MethodProvider.sleep(2500);
        } else {
            script.log(teleportItemName + " not found in inventory.");
        }
    }
}