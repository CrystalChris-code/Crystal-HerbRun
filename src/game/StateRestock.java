package game;

import data.CompostTypes;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class StateRestock {
    private final Script script;
    private final CompostTypes compostType;

    // Opdateret constructor med CompostTypes som parameter
    public StateRestock(Script script, CompostTypes compostType) {
        this.script = script;
        this.compostType = compostType;
    }

    public void execute() throws InterruptedException {
        // Åbn banken
        if (!script.getBank().isOpen()) {
            script.getBank().open();
            new ConditionalSleep(5000, 250) {
                @Override
                public boolean condition() throws InterruptedException {
                    return script.getBank().isOpen();
                }
            }.sleep();
        }

        if (!script.getBank().isOpen()) {
            script.log("Banken kunne ikke åbnes.");
            return;
        }

        // Depositér alle buckets
        for (Item item : script.getInventory().getItems()) {
            if (item != null && item.getName() != null && item.getName().toLowerCase().contains("bucket")) {
                script.log("Depositing bucket: " + item.getName());
                script.getBank().deposit(item.getName(), 0); // depositér alle forekomster
                MethodProvider.sleep(1000);
            }
        }

        // Depositér alle items med "Grimy" i navnet
        for (Item item : script.getInventory().getItems()) {
            if (item != null && item.getName() != null && item.getName().toLowerCase().contains("grimy")) {
                script.log("Depositing grimy herb: " + item.getName());
                script.getBank().deposit(item.getName(), 0);
                MethodProvider.sleep(1000);
            }
        }

        // Tjek for compostType
        if (compostType == null) {
            script.log("Compost type er null. Restock springes over.");
        } else {
            String compostItemName = compostType.getItemName();
            int currentCount = (int) script.getInventory().getAmount(compostItemName);
            int amountNeeded = 5 - currentCount;
            if (amountNeeded > 0) {
                if (script.getBank().contains(compostItemName)) {
                    script.log("Withdrawing " + amountNeeded + " " + compostItemName);
                    script.getBank().withdraw(compostItemName, amountNeeded);
                    new ConditionalSleep(3000, 250) {
                        @Override
                        public boolean condition() throws InterruptedException {
                            return script.getInventory().getAmount(compostItemName) == currentCount + amountNeeded;
                        }
                    }.sleep();
                } else {
                    script.log("Banken indeholder ikke " + compostItemName);
                }
            } else {
                script.log("Der er allerede " + currentCount + " " + compostItemName + " i inventory.");
            }
        }

        // Luk banken
        script.getBank().close();
    }
}