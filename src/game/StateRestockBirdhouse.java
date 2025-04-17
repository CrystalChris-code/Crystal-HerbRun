package game;

import data.BirdhouseSeedTypes;
import data.LogTypes;
import org.osbot.Sc;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateRestockBirdhouse {

    private final Script script;
    private final BirdhouseSeedTypes selectedSeed;
    private final LogTypes selectedLogs;

    public StateRestockBirdhouse(Script script, BirdhouseSeedTypes selectedSeed, LogTypes selectedLogs) {
        this.script = script;
        this.selectedSeed = selectedSeed;
        this.selectedLogs = selectedLogs;
    }

    public void execute() throws InterruptedException {
        // Step 1: Open closest bank
        if (!script.getBank().isOpen()) {
            if (script.getBank().open()) {
                MethodProvider.sleep(600);
            } else {
                script.log("Failed to open bank.");
                return;
            }
        }

        // Step 2: Deposit all items
        if (script.getBank().depositAll()) {
            MethodProvider.sleep(500);
        }

        // Step 3: Withdraw materials
        String seedName = selectedSeed.getBirdhouseSeedName();
        String logName = selectedLogs.getLogName();

        withdrawAll(seedName, "seeds");
        withdrawAll("Varrock teleport", "Varrock teleport");
        withdrawItem("Chisel", 1);
        withdrawItem("Hammer", 1);
        withdrawItem(logName, 4);
        withdrawItem("Clockwork", 4);
        if (script.getBank().contains("Digsite pendant (1)")) {
            script.getBank().withdraw("Digsite pendant (1)",1);
            MethodProvider.sleep(250);
        } else if (script.getBank().contains("Digsite pendant (2)")) {
            script.getBank().withdraw("Digsite pendant (2)",1);
            MethodProvider.sleep(250);
        } else if (script.getBank().contains("Digsite pendant (3)")) {
            script.getBank().withdraw("Digsite pendant (3)",1);
            MethodProvider.sleep(250);
        } else if (script.getBank().contains("Digsite pendant (4)")) {
            script.getBank().withdraw("Digsite pendant (4)",1);
            MethodProvider.sleep(250);
        } else if (script.getBank().contains("Digsite pendant (5)")) {
            script.getBank().withdraw("Digsite pendant (5)",1);
            MethodProvider.sleep(250);
        } else {
            script.log("Missing Digsite Pendant :(");
            MethodProvider.sleep(250);
        }

        // Step 5: Close bank
        script.getBank().close();
        MethodProvider.sleep(600);
    }

    private void withdrawItem(String name, int amount) throws InterruptedException {
        if (script.getBank().contains(name)) {
            script.getBank().withdraw(name, amount);
            MethodProvider.sleep(300);
        } else {
            script.log("Missing item in bank: " + name);
        }
    }

    private void withdrawAll(String name, String label) throws InterruptedException {
        if (script.getBank().contains(name)) {
            script.getBank().withdrawAll(name);
            MethodProvider.sleep(300);
            script.log("Withdrew all " + label);
        } else {
            script.log("No " + label + " found in bank.");
        }
    }
}
