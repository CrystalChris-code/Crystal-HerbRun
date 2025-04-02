package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateTeleportToArdougne {

    private final Script script;

    public StateTeleportToArdougne(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        // 1. Find og brug "Ardougne teleport" i inventory
        String teleportItemName = "Ardougne teleport";
        if (script.getInventory().contains(teleportItemName)) {
            script.log("Using " + teleportItemName);
            script.getInventory().getItem(teleportItemName).interact("Break");

            MethodProvider.sleep(5000);

            Area ArodugnePatch = new Area(2668, 3377, 2673, 3372);
            script.log("Walking to Ardougne herb patch...");
            script.getWalking().webWalk(ArodugnePatch);
            MethodProvider.sleep(2500);
        } else {
            script.log(teleportItemName + " not found in inventory.");
        }
    }
}