package game;

import data.CompostTypes;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateCompostHerbs {

    private final Script script;
    private final CompostTypes compostType;

    public StateCompostHerbs(Script script, CompostTypes compostType) {
        this.script = script;
        this.compostType = compostType;
    }

    public void execute() throws InterruptedException {
        if (compostType == CompostTypes.NONE) return;

        RS2Object herbPatch = script.getObjects().closest("Herb patch");

        if (herbPatch == null) {
            script.log("No herb patch found.");
            return;
        }

        if (!script.getInventory().contains(compostType.getItemName())) {
            script.log("No " + compostType.getItemName() + " in inventory.");
            return;
        }

        script.log("Using " + compostType.getItemName() + " on patch...");

        // Klik "Use" på compost
        if (script.getInventory().getItem(compostType.getItemName()).interact("Use")) {
            MethodProvider.sleep(2000); // kort pause

            // Klik på herb patch
            if (herbPatch.interact("Use")) {
                MethodProvider.sleep(5000); // lad animationen køre
            } else {
                script.log("Failed to click patch.");
            }
        } else {
            script.log("Failed to click compost.");
        }
    }
}
