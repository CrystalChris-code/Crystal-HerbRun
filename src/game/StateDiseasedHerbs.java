package game;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

public class StateDiseasedHerbs { private final Script script;

    public StateDiseasedHerbs(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        // Find et herb-patch med action "Cure"
        RS2Object diseasedPatch = script.getObjects().closest(o ->
                o.getDefinition() != null &&
                        o.getDefinition().getName().toLowerCase().contains("herb") &&
                        o.hasAction("Cure")
        );

        if (diseasedPatch == null) {
            script.log("Ingen syge herb patches fundet.");
            return;
        }

        script.log("Starter curing af syg herb patch...");

        // Loop indtil patchen ikke længere er syg (altså ikke har action "Cure")
        while (diseasedPatch.hasAction("Cure")) {
            // Tjek at spaden findes i inventory
            if (!script.getInventory().contains("Spade")) {
                script.log("Spade ikke fundet i inventory. Afbryder curing.");
                return;
            }

            Item spade = script.getInventory().getItem("Spade");
            if (spade != null && spade.interact("Use")) {
                MethodProvider.sleep(500);
                if (diseasedPatch.interact("Use")) {
                    MethodProvider.sleep(1000);
                    // Vent op til 10 sekunder på, at en dialogboks vises
                    new ConditionalSleep(10000, 250) {
                        @Override
                        public boolean condition() throws InterruptedException {
                            return script.getDialogues().isPendingOption();
                        }
                    }.sleep();

                    if (script.getDialogues().isPendingOption()) {
                        script.log("Dialog opdaget – vælger 'Yes, I want to clear it for new crops'.");
                        script.getDialogues().selectOption(1);
                        MethodProvider.sleep(1000);
                    } else {
                        script.log("Ingen dialog opdaget, muligvis allerede cured.");
                    }
                } else {
                    script.log("Kunne ikke interagere med patchen.");
                }
            } else {
                script.log("Kunne ikke bruge spaden.");
            }
            // Giv patchen tid til at opdatere sin status
            MethodProvider.sleep(2000);
            // Genopfrisk eller hent patchen igen
            diseasedPatch = script.getObjects().closest(o ->
                    o.getDefinition() != null &&
                            o.getDefinition().getName().toLowerCase().contains("herb") &&
                            o.hasAction("Cure")
            );
        }
        script.log("Patchen er nu cured.");
    }
}