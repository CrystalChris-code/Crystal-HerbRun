package game;

import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateDropWeeds {
    private final Script script;

    public StateDropWeeds(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        for (Item item : script.getInventory().getItems()) {
            if (item != null && item.getName() != null && item.getName().toLowerCase().contains("weeds")) {
                script.log("Dropping: " + item.getName());
                item.interact("Drop");
                MethodProvider.sleep(400); // Kort pause for at lade drop-interaktionen fuldføre
            }
        }
    }
}