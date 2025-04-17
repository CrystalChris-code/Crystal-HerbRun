package game;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateHarvestBirdhouse {

    private final Script script;

    public StateHarvestBirdhouse(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        RS2Object readyBirdhouse = script.getObjects().closest(o ->
                o.getName().toLowerCase().contains("birdhouse") && o.hasAction("Empty"));

        if (readyBirdhouse != null) {
            if (!readyBirdhouse.isVisible()) {
                script.getCamera().toEntity(readyBirdhouse);
                MethodProvider.sleep(300);
            }

            if (readyBirdhouse.interact("Empty")) {
                script.log("Emptied birdhouse...");

                // Wait until the action "Empty" is no longer present (means it's been emptied)
                int wait = 0;
                while (readyBirdhouse.hasAction("Empty") && wait < 3000) {
                    MethodProvider.sleep(200);
                    wait += 200;
                }
            }
        } else {
            script.log("No birdhouse ready for harvesting.");
        }

        MethodProvider.sleep(1500); // short cooldown before next state
    }
}
