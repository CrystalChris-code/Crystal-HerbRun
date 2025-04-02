package game;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.utility.ConditionalSleep;

public class StateHarvestHerbs {
    private final Script script;

    public StateHarvestHerbs(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        RS2Object herbPatch = script.getObjects().closest(
                o -> o.getDefinition() != null &&
                        o.getDefinition().getName().toLowerCase().contains("herb") &&
                        o.hasAction("Pick")
        );

        if (herbPatch == null) {
            return;
        }

        script.log("Harvesting herbs...");
        herbPatch.interact("Pick");

        long endTime = System.currentTimeMillis() + 30000; // Vent i op til 30 sekunder
        while (System.currentTimeMillis() < endTime) {
            // Hvis herbPatch ikke længere har "Pick", så er høstningen færdig – bryd ud.
            if (!herbPatch.hasAction("Pick")) {
                break;
            }
            // Hvis en dialogue (fx level up) er aktiv, kan vi forsøge at interagere med herbPatch igen
            if (script.getDialogues().isPendingContinuation()) {
                if (herbPatch.hasAction("Pick")) {
                    script.log("Dialogue detected; re-attempting to pick herbs.");
                    herbPatch.interact("Pick");
                } else {
                    break;
                }
            }
            MethodProvider.sleep(250);
        }
        MethodProvider.sleep(1500);
    }
}
