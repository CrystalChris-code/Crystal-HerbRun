package game;

import data.LogTypes;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import java.awt.event.KeyEvent;

public class StateCreateBirdhouse {

    private final Script script;
    private final LogTypes selectedLogType;

    public StateCreateBirdhouse(Script script, LogTypes selectedLogType) {
        this.script = script;
        this.selectedLogType = selectedLogType;
    }

    public void execute() throws InterruptedException {
        String logName = selectedLogType.getLogName();

        if (script.getInventory().contains(logName) && script.getInventory().contains(8792)) {
            script.log("Using " + logName + " with Clockwork");

            if (script.getInventory().getItem(logName).interact("Use")) {
                MethodProvider.sleep(600);

                if (script.getInventory().interact("Use", 8792)) {
                    MethodProvider.sleep(1000);

                    script.log("Waiting for crafting interface...");
                    int wait = 0;
                    while (script.getWidgets().getWidgetContainingText("How many do you wish to make?") == null && wait < 3000) {
                        MethodProvider.sleep(200);
                        wait += 200;
                    }

                    // Step 4: Click "All"
                    script.log("Clicking 'All' to craft all birdhouses...");
                    RS2Widget allButton = script.getWidgets().getWidgetContainingText("All");

                    if (allButton != null && allButton.isVisible()) {
                        allButton.interact();
                        MethodProvider.sleep(600);

                        // Step 5: Press "1" on keyboard to craft birdhouse
                        script.log("Pressing key 1 to select Bird house...");
                        script.getKeyboard().typeKey((char) KeyEvent.VK_1);
                        MethodProvider.sleep(500);

                        // Step 6: Wait until logs are gone
                        while (script.getInventory().contains(logName)) {
                            MethodProvider.sleep(1000);
                        }

                    } else {
                        script.log("Could not find 'All' button.");
                    }
                }
            }

        } else {
            script.log("Missing required materials (log or clockwork).");
        }

        MethodProvider.sleep(1000);
    }
}
