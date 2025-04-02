package game;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class StateDeadHerbs {

    private final Script script;

    public StateDeadHerbs(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        RS2Object deadPatch = script.getObjects().closest(
                o -> o.getDefinition() != null &&
                        o.getDefinition().getName().toLowerCase().contains("herb") &&
                        o.hasAction("Clear")
        );

        if (deadPatch == null) {
            return; // Ingen døde herbs fundet
        }

        script.log("Clearing dead herbs...");
        if (deadPatch.interact("Clear")) {
            new ConditionalSleep(10000, 250) {
                @Override
                public boolean condition() throws InterruptedException {
                    return !deadPatch.hasAction("Clear");
                }
            }.sleep();

            MethodProvider.sleep(3000); // Lidt ekstra pause
        }
    }
}
