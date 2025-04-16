package game;

import org.osbot.A;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateTeleportToTitheFarm {
    private static final Area HosidiusPatch = new Area(1735, 3553, 1741, 3548);



    private final Script script;

    public StateTeleportToTitheFarm(Script script) {
        this.script = script;
    }

    public void execute() throws InterruptedException {
        MethodProvider.sleep(1500);
        if (script.getDialogues().isPendingContinuation()) {
            script.getDialogues().isPendingContinuation();
            MethodProvider.sleep(1500);
        }

        ensureClanChatTab();
        ensureGroupingSubTab();

        if (script.getWidgets().interact(76,32,"Teleport to Tithe Farm")) {
            script.log("Teleporting to Tithe Farm!");
            MethodProvider.sleep(20000);

            script.log("Walking to Hosidius Patch!");
            script.getWalking().webWalk(HosidiusPatch);
            MethodProvider.sleep(2500);
            return;
        } else {
            script.log("Tithe Farm Minigame not selected! :(");
        }

        IsActivitySelectionOpen();

        if (script.getWidgets().isVisible(76,22,23)
                && script.getWidgets().interact(76,22,23,"Select")
                || script.getWidgets().interact(76,22,22,"Select")
                && !script.getWidgets().interact(76,32,"Teleport to TitheFarm")) {
            script.log("Tithe Farm visible. Selecting Tithe Farm Minigame!");
            MethodProvider.sleep(1500);
        } else {
            CouldNotInteract();
            MethodProvider.sleep(1500);
        }

        if (!CouldNotInteract()
                && script.getWidgets().isVisible(76,22,23)
                && script.getWidgets().isVisible(76,22)) {

            script.log("Scrolling to find Tithe Farm!");
            script.getMouse().move(555,275);
            MethodProvider.sleep(1500);
            for (int i = 0; i < 10; i++) {
                script.getMouse().scrollDown();
                MethodProvider.sleep(100);
            }
        }

        if (script.getWidgets().get(76,22,22).getMessage().equalsIgnoreCase("Tithe Farm")) {
            script.getWidgets().interact(76,22,22,"Select");
            script.log("Selecting Tithe Farm Minigame!");
            MethodProvider.sleep(1500);
        } else if (script.getWidgets().get(76,22,23).getMessage().equalsIgnoreCase("Tithe Farm")) {
            script.getWidgets().interact(76,22,23,"Select");
            script.log("Selecting Tithe Farm Minigame!");
            MethodProvider.sleep(1500);
        } else {
            script.log("Could not select widget....");
        }

        if (script.getWidgets().interact(76,32,"Teleport to Tithe Farm")) {
            script.log("Teleporting to Tithe Farm!");
            MethodProvider.sleep(20000);

            script.log("Walking to Hosidius Patch!");
            script.getWalking().webWalk(HosidiusPatch);
            MethodProvider.sleep(2500);
        }

    }

    private void OpenActivitySelection() throws InterruptedException {
        if (script.getWidgets().isVisible(76,26)) {
            script.getWidgets().interact(76,24,"Cancel");
            script.log("Opening activity selection!");
            MethodProvider.sleep(2500);
        }
    }

    private boolean CouldNotInteract () throws InterruptedException {
        script.log("Could not interact with TitheFarm!");
        MethodProvider.sleep(2500);
        return false;
    }

    private void IsActivitySelectionOpen() throws InterruptedException {
        if (!script.getWidgets().isVisible(76,16,0)) {
            script.log("Activity selection is open!");
            MethodProvider.sleep(1000);
        } else {
            OpenActivitySelection();
            MethodProvider.sleep(1000);
        }
    }

    private void ensureGroupingSubTab() throws InterruptedException {
        if (tryInteractSubWidget()) return;
        MethodProvider.random(1500,2000);
    }

    private boolean tryInteractSubWidget() throws InterruptedException {
        if (script.getWidgets().interact(707, 5, 5, "Grouping")) {
            script.log("Clicking Grouping");
            MethodProvider.sleep(2500);

        }
        return false;
    }

    private void ensureClanChatTab() throws InterruptedException {
        if (tryInteractWidget("Grouping", "Clicked Grouping menu")) return;
        if (tryInteractWidget("Chat-Channel", "Clicked Chat-Channel menu")) return;
        if (tryInteractWidget("Your Clan", "Clicked Your Clan menu")) return;
        if (tryInteractWidget("View another clan", "Clicked View another clan menu")) return;
        script.log("Could not interact with clan chat tab at (548,54)");
        MethodProvider.sleep(3000);
    }

    private boolean tryInteractWidget(String action, String logMsg) throws InterruptedException {
        if (script.getWidgets().interact(548, 54, action)) {
            script.log(logMsg);
            MethodProvider.random(2500, 4000);
            return true;
        }
        return false;
    }
}
