package game;

import data.CompostTypes;
import data.HerbTypes;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class StateRestock {
    private final Script script;
    private final CompostTypes compostType;
    private final HerbTypes selectedHerb;

    public StateRestock(Script script, CompostTypes compostType, HerbTypes selectedHerb) {
        this.script = script;
        this.compostType = compostType;
        this.selectedHerb = selectedHerb;
    }

    public void execute() throws InterruptedException {
        // Åbn bank
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

        // Depositér alt
        script.getBank().depositAll();
        MethodProvider.sleep(500);

        // Step 1: Frø
        withdrawAll(selectedHerb.getSeedName(), "seeds");

        // Step 2: Tools
        withdrawItem("Seed dibber", 1);
        withdrawItem("Spade", 1);
        withdrawItem("Rake", 1);

        // Step 3: Teleports
        withdrawAll("Camelot teleport", "Camelot teleport");
        withdrawAll("Ardougne teleport", "Ardougne teleport");
        withdrawAll("Falador teleport", "Falador teleport");
        withdrawAll("Varrock teleport", "Varrock teleport");

        // Step 4: Ectophial
        withdrawItem("Ectophial", 1);

        // Step 5: Compost – beholdes som det er
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
        MethodProvider.sleep(500);
    }

    private void withdrawItem(String name, int amount) throws InterruptedException {
        if (script.getBank().contains(name)) {
            script.getBank().withdraw(name, amount);
            MethodProvider.sleep(300);
        } else {
            script.log("Mangler item i bank: " + name);
        }
    }

    private void withdrawAll(String name, String label) throws InterruptedException {
        if (script.getBank().contains(name)) {
            script.getBank().withdrawAll(name);
            MethodProvider.sleep(300);
            script.log("Withdrew all " + label);
        } else {
            script.log("Mangler " + label + " i bank.");
        }
    }
}
