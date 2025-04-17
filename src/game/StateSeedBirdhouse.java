package game;

import data.BirdhouseSeedTypes;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateSeedBirdhouse {

    private final Script script;
    private final BirdhouseSeedTypes selectedSeed;

    public StateSeedBirdhouse(Script script, BirdhouseSeedTypes selectedSeed) {
        this.script = script;
        this.selectedSeed = selectedSeed;
    }

    public void execute() throws InterruptedException {
        String seedName = selectedSeed.getBirdhouseSeedName();
        Item seed = script.getInventory().getItem(seedName);
        RS2Object plantedBirdhouse = script.getObjects().closest(o ->
                o.getName().toLowerCase().contains("birdhouse") && o.hasAction("Seeds"));

        if (seed != null && plantedBirdhouse != null) {
            if (!plantedBirdhouse.isVisible()) {
                script.getCamera().toEntity(plantedBirdhouse);
                MethodProvider.sleep(300);
            }

            if (seed.interact("Use")) {
                // Wait until selected
                int wait = 0;
                while (!script.getInventory().isItemSelected() && wait < 1000) {
                    MethodProvider.sleep(100);
                    wait += 100;
                }

                if (script.getInventory().isItemSelected()) {
                    if (plantedBirdhouse.interact("Use")) {
                        script.log("Filled birdhouse with seed: " + seedName);

                        // Wait until seed is removed from inventory
                        wait = 0;
                        while (script.getInventory().contains(seedName) && wait < 3000) {
                            MethodProvider.sleep(200);
                            wait += 200;
                        }
                    }
                } else {
                    script.log("Failed to select seed.");
                }
            }
        } else {
            script.log("Seed or filled birdhouse not found.");
        }

        MethodProvider.sleep(1500); // small delay before exiting state
    }
}
