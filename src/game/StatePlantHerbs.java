package game;

import data.HerbTypes;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;


public class StatePlantHerbs {
    private final Script script;
    private final HerbTypes selectedHerb;

    public StatePlantHerbs(Script script, HerbTypes selectedHerb) {
        this.script = script;
        this.selectedHerb = selectedHerb;
    }


    public void execute() throws InterruptedException {
        RS2Object herbpatch = script.getObjects().closest("Herb patch");
        if (herbpatch == null) return;

        if (herbpatch.hasAction("Rake")) {
            herbpatch.interact("Rake");
            new ConditionalSleep(50000,250) {
                @Override
                public boolean condition() throws InterruptedException {
                    return !herbpatch.hasAction("Rake");
                }
            }.sleep();
        }
        MethodProvider.sleep(2000);
        String seedName = selectedHerb.getSeedName();
        if (script.getInventory().contains(seedName)) {
            script.log("Planting seed: " + seedName);
            script.getInventory().getItem(seedName).interact("Use");
            MethodProvider.sleep(1000);
            herbpatch.interact("Use");
            MethodProvider.sleep(1000);
        } else {
            script.log("No " + seedName + " found in inventory!");
        }

    }
}
