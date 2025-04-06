package game;

import data.GrimyHerbTypes;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateNoteHerbs {

    private final Script script;

    public StateNoteHerbs(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        for (Item item : script.getInventory().getItems()) {
            if (item == null) continue;

            for (GrimyHerbTypes grimyHerb : GrimyHerbTypes.values()) {
                if (item.getId() == grimyHerb.getId()) {
                    script.log("Noting herb: " + grimyHerb.getName());
                    if (item.interact("Use")) {
                        MethodProvider.sleep(800);
                        NPC toolNPC = script.getNpcs().closest("Tool Leprechaun");
                        if (toolNPC != null) {
                            toolNPC.interact("Use");
                            MethodProvider.sleep(2500);
                        } else {
                            script.log("Tool Leprechaun ikke fundet!");
                        }

                        if (script.getDialogues().isPendingContinuation()) {
                            script.getDialogues().completeDialogue();
                            MethodProvider.sleep(3000); // kort pause, så dialogen opdaterer
                        }

                    }
                    break;
                }
            }
        }
    }
}
