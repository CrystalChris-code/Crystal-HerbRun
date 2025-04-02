package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateTeleportToFalador {

    private final Script script;

    public StateTeleportToFalador(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        String teleportItemName = "Falador teleport";
        if (script.getInventory().contains(teleportItemName)) {
            script.log("Using " + teleportItemName);
            script.getInventory().getItem(teleportItemName).interact("Break");

            MethodProvider.sleep(5000);

            Area FaladorPatch = new Area(3056, 3314, 3061, 3309);
            script.log("Walking to Falador herb patch...");
            script.getWalking().webWalk(FaladorPatch);
            MethodProvider.sleep(2500);
        } else {
            script.log(teleportItemName + " not found in inventory.");
        }
    }
}