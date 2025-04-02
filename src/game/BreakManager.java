package game;

import org.osbot.rs07.script.RandomEvent;
import org.osbot.rs07.script.RandomSolver;

public class BreakManager extends RandomSolver {

    private long breakUntil = 0;

    public BreakManager(RandomEvent event) {
        super(event);
    }

    public void activateBreakFor(long durationMillis) {
        this.breakUntil = System.currentTimeMillis() + durationMillis;
    }

    @Override
    public boolean shouldActivate() {
        return System.currentTimeMillis() < breakUntil;
    }

    @Override
    public int onLoop() throws InterruptedException {
        if (getClient().isLoggedIn()) {
            getLogoutTab().open();
            getWidgets().interact(548, 57, "Logout");
            sleep(1000);
            getWidgets().interact(182, 9, "Logout");
            sleep(1000);
            log("[BreakManager] Logging out for break...");
        } else {
            log("[BreakManager] Still breaking...");
        }
        return 1000;
    }

    @Override
    public String getName() {
        return "BreakManager";
    }
}
