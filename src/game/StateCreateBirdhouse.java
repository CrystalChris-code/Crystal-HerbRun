package game;

import data.LogTypes;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

import java.lang.reflect.Method;

public class StateCreateBirdhouse {

    private final Script script;
    private final LogTypes selectedLogType;

    public StateCreateBirdhouse(Script script, LogTypes selectedLogType) {
        this.script = script;
        this.selectedLogType = selectedLogType;
    }

    public void execute() throws InterruptedException {
        String logName = selectedLogType.getLogName();
        if (script.getInventory().contains(logName)) {
            script.log("Using" + logName);
            script.getInventory().getItem(logName).interact("Use");
            MethodProvider.sleep(1000);
            script.getInventory().interact("Use",8792);
            MethodProvider.sleep(250);
        } else {
            script.log("No" + logName + "Found in inventory");
        }
    }
}