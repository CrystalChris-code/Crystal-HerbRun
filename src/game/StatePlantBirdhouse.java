package game;

import data.LogTypes;
import org.osbot.Me;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StatePlantBirdhouse {

    private final Script script;
    private final String[] BIRDHOUSE_TYPES = {
            "Bird house",
            "Oak bird house",
            "Willow bird house",
            "Teak bird house",
            "Maple bird house",
            "Mahogany bird house",
            "Yew bird house",
            "Magic bird house",
            "Redwood bird house"
    };

    public StatePlantBirdhouse(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        Item birdhouseItem = script.getInventory().getItem(BIRDHOUSE_TYPES);
        RS2Object birdhouseSpot = script.getObjects().closest("Space");

        if (birdhouseItem != null && birdhouseSpot != null) {
            if (!birdhouseSpot.isVisible()) {
                script.getCamera().toEntity(birdhouseSpot);
                MethodProvider.sleep(300); // allow time for camera movement
            }

            if (birdhouseItem.interact("Use")) {
                // Wait until item is selected (max 1 second)
                int wait = 0;
                while (!script.getInventory().isItemSelected() && wait < 1000) {
                    MethodProvider.sleep(100);
                    wait += 100;
                }

                if (script.getInventory().isItemSelected()) {
                    if (birdhouseSpot.interact("Use")) {
                        script.log("Using Bird house on Space...");

                        // Optional: wait until item is no longer in inventory
                        wait = 0;
                        while (script.getInventory().contains(BIRDHOUSE_TYPES) && wait < 3000) {
                            MethodProvider.sleep(200);
                            wait += 200;
                        }
                    }
                } else {
                    script.log("Failed to select Bird house.");
                }
            }
        } else {
            script.log("Bird house item or Space object not found.");
        }
        MethodProvider.sleep(1500);
    }

}
