package game;

import data.BirdhouseSeedTypes;
import data.LogTypes;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

import java.lang.reflect.Method;

public class StateSeedBirdhouse {

    private final Script script;
    private final BirdhouseSeedTypes selectedSeed;

    public StateSeedBirdhouse(Script script, BirdhouseSeedTypes selectedSeed) {
        this.script = script;
        this.selectedSeed = selectedSeed;
    }

    public void execute() throws InterruptedException {

    }
}