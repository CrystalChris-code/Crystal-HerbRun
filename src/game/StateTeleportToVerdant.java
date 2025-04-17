package game;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

import java.awt.event.KeyEvent;

public class StateTeleportToVerdant {

    private final Script script;

    public StateTeleportToVerdant(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        RS2Object mushtree = script.getObjects().closest("Magic Mushtree");

        if (mushtree != null) {
            if (!mushtree.isVisible()) {
                script.getCamera().toEntity(mushtree);
                MethodProvider.sleep(300);
            }

            script.log("Interacting with Magic Mushtree...");

            if (mushtree.interact("Use")) {
                MethodProvider.sleep(3000); // Wait for interface to appear

                script.log("Selecting Verdant Valley (option 2)...");
                script.getKeyboard().typeKey((char) KeyEvent.VK_2);

                MethodProvider.sleep(3000); // Wait for teleport animation to finish
            }
        } else {
            script.log("Magic Mushtree not found.");
        }

        MethodProvider.sleep(3000);
    }
}
