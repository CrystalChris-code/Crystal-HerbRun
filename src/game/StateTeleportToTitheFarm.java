package game;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class StateTeleportToTitheFarm {
    private final Script script;

    public StateTeleportToTitheFarm(Script script) {
        this.script = script;
    }

    private static final String[] WIDGET_ACTIONS = {
            "Grouping", "Chat-Channel", "Your Clan", "View another clan"
    };

    public void execute() throws InterruptedException {
        MethodProvider.sleep(3000);

        if (script.getWidgets().interact(548,54,"Grouping")){
            script.getWidgets().interact(548,54,"Grouping");
            script.log("548,54,Grouping");
            MethodProvider.random(2500,4000);
            } else if (script.getWidgets().interact(548,54,"Chat-Channel")) {
            script.getWidgets().interact(548,54,"Chat-Channel");
            script.log("548,54,Chat-Channel");
            MethodProvider.random(2500,4000);
                } else if (script.getWidgets().interact(548,54,"Your Clan")) {
                script.getWidgets().interact(548,54,"Your Clan");
                script.log("548,54,Your Clan");
                MethodProvider.random(2500,4000);
                    } else if (script.getWidgets().interact(548,54,"View another clan")) {
                    script.getWidgets().interact(548,54,"View another clan");
                    script.log("548,54,View another clan");
                    MethodProvider.random(2500,4000);
        } else {
            script.log("Could not interact with 548,54");
        }
        MethodProvider.sleep(2500);

        if (script.getWidgets().interact(76,32,"Teleport to Tithe Farm")) {
            script.getWidgets().interact(76,32,"Teleport to Tithe Farm");
            MethodProvider.sleep(20000);
            return; // Afslutter metoden, så koden nedenfor bliver ikke udført
        }

        if (!script.getWidgets().isVisible(76,1)) {
            script.getWidgets().interact(707,5,5,"Grouping");
            script.log("Opening Grouping menu!");
            MethodProvider.random(2500,4000);
        }
        MethodProvider.sleep(2500);

        if (script.getWidgets().interact(76,32,"Teleport to Tithe Farm")) {
            script.getWidgets().interact(76,32,"Teleport to Tithe Farm");
            MethodProvider.sleep(20000);
            return; // Afslutter metoden, så koden nedenfor bliver ikke udført
        }

        if (script.getWidgets().isVisible(76,24)) {
            script.getWidgets().interact(76,24,"Cancel");
            script.log("Opening activity selection!");
            MethodProvider.sleep(2500);
        }

        MethodProvider.sleep(2500);

        int attempts = 0;
        final int MAX_ATTEMPTS = 20;

        while (attempts < MAX_ATTEMPTS) {
            // Try to interact with the widget
            if (script.getWidgets().interact(76, 22, 22, "Select")) {
                script.log("Widget found and interacted with.");
                break;
            }

            script.getMouse().getWidgets().mouse.move(555, 275);
            script.getMouse().scrollDown();
            script.getMouse().scrollDown();
            script.getMouse().scrollDown();
            script.getMouse().scrollDown();
            script.getMouse().scrollDown();

            attempts++;
        }

        if (attempts >= MAX_ATTEMPTS) {
            script.log("Widget not found after scrolling.");
        }

        if (script.getWidgets().isVisible(76,32)) {
            script.getWidgets().interact(76,32,"Teleport to Tithe Farm");
            MethodProvider.sleep(20000);
        }



    }
}