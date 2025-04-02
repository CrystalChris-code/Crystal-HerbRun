package game;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateTeleportToVarrock {

    private final Script script;

    public StateTeleportToVarrock(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        // 1. Find og brug "Camelot teleport" i inventory
        String teleportItemName = "Varrock teleport";
        if (script.getInventory().contains(teleportItemName)) {
            script.log("Using " + teleportItemName);
            script.getInventory().getItem(teleportItemName).interact("Break");
            // Kort pause for teleportationen
            MethodProvider.sleep(5000);
            // 2. Webwalk til herb patch i Catherby – igen skal du bruge de korrekte koordinater
            Area VarrockBank = new Area(3184, 3434, 3180, 3444); // Eksempelkoordinater for Catherby
            script.log("Walking to Varrock bank...");
            script.getWalking().webWalk(VarrockBank);
            MethodProvider.sleep(2500);
        } else {
            script.log(teleportItemName + " not found in inventory.");
        }
    }
}