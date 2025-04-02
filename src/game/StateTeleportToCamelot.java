package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateTeleportToCamelot {

    private final Script script;

    public StateTeleportToCamelot(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        // 1. Find og brug "Camelot teleport" i inventory
        String teleportItemName = "Camelot teleport";
        if (script.getInventory().contains(teleportItemName)) {
            script.log("Using " + teleportItemName);
            script.getInventory().getItem(teleportItemName).interact("Break");
            // Kort pause for teleportationen
            MethodProvider.sleep(5000);
            // 2. Webwalk til herb patch i Catherby – igen skal du bruge de korrekte koordinater
            Area Catherby = new Area(2810, 3466, 2815, 3461); // Eksempelkoordinater for Catherby
            script.log("Walking to Catherby herb patch...");
            script.getWalking().webWalk(Catherby);
            MethodProvider.sleep(2500);
        } else {
            script.log(teleportItemName + " not found in inventory.");
        }
    }
}