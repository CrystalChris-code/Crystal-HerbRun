package game;

import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateTeleportToDigsite {

    private final Script script;
    private final String[] DIGSITE = {
            "Digsite pendant (5)",
            "Digsite pendant (4)",
            "Digsite pendant (3)",
            "Digsite pendant (2)",
            "Digsite pendant (1)"
    };

    public StateTeleportToDigsite(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        Item digsitePendant = script.getInventory().getItem(DIGSITE);

        if (digsitePendant != null) {
            script.log("Found Digsite Pendant: " + digsitePendant.getName());

            if (digsitePendant.interact("Rub")) {
                script.log("Rubbing Digsite Pendant...");
                MethodProvider.sleep(1000); // wait for teleport interface to appear

                // Try to click "Digsite" from the teleport menu
                RS2Widget digsiteOption = script.getWidgets().getWidgetContainingText("Digsite");

                if (digsiteOption != null && digsiteOption.isVisible()) {
                    if (digsiteOption.interact()) {
                        script.log("Teleporting to Digsite...");
                        MethodProvider.sleep(5000); // wait for teleport animation
                    }
                } else {
                    script.log("Teleport option 'Digsite' not found.");
                }
            }
        } else {
            script.log("No Digsite pendant found.");
        }

        MethodProvider.sleep(1000); // short pause before moving to next state
    }
}
