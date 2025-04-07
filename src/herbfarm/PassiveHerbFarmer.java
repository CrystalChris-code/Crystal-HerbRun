package herbfarm;

import game.*;
import data.*;
import org.osbot.MB;
import org.osbot.core.AbstractMethodProvider;
import org.osbot.rs07.script.*;
import data.HerbTypes;
import gui.HerbGuiHandler; // Import the GUI handler
import data.CompostTypes;
import org.osbot.rs07.script.RandomEvent;
import org.osbot.rs07.script.RandomSolver;
import org.osbot.rs07.utility.ConditionalSleep;


import javax.swing.plaf.nimbus.State;


@ScriptManifest(
        author = "CrystalChris",
        name = "Crystal Herbrun",
        info = "Starts with planting herbs",
        version = 1.0,
        logo = "https://i.imgur.com/SQcVtUU.jpg"
)
public class PassiveHerbFarmer extends Script {

    private final HerbState currentState = HerbState.PLANT_HERBS;
    private StatePlantHerbs statePlantHerbs;
    private StateCompostHerbs stateCompostHerbs;
    private StateHarvestHerbs stateHarvestHerbs;
    private StateDeadHerbs stateDeadHerbs;
    private StateTeleportToArdougne stateTeleportToArdougne;
    private StateTeleportToCamelot stateTeleportToCamelot;
    private StateTeleportToVarrock stateTeleportToVarrock;
    private StateTeleportToFalador stateTeleportToFalador;
    private StateTeleportToPhasmatys stateTeleportToPhasmatys;
    private StateDropWeeds stateDropWeeds;
    private StateNoteHerbs stateNoteHerbs;
    private StateRestock stateRestock;
    private StateDiseasedHerbs stateDiseasedHerbs;
    private BreakManager breakManager;
    private StateTeleportToTitheFarm stateTeleportToTitheFarm;
    private StateWalkToHosidius stateWalkToHosidius;
    private boolean hasHarvested = false;
    private boolean hasComposted = false;



    @Override
    public void onStart() {
        log("Opening GUI to select a seed and compost...");
        breakManager = new BreakManager(RandomEvent.BREAK_MANAGER);
        breakManager.exchangeContext(getBot());
        getBot().getRandomExecutor().overrideOSBotRandom(breakManager);

        HerbGuiHandler gui = new HerbGuiHandler();
        HerbTypes selectedHerb = gui.getSelectedHerb();
        CompostTypes selectedCompost = gui.getSelectedCompost();

        log("Selected Seed: " + selectedHerb.getSeedName());
        log("Selected Compost: " + selectedCompost.getItemName());

        stateCompostHerbs = new StateCompostHerbs(this, selectedCompost);
        statePlantHerbs = new StatePlantHerbs(this, selectedHerb);
        stateHarvestHerbs = new StateHarvestHerbs(this);
        stateDeadHerbs = new StateDeadHerbs(this);
        stateTeleportToArdougne = new StateTeleportToArdougne(this);
        stateTeleportToCamelot = new StateTeleportToCamelot(this);
        stateTeleportToVarrock = new StateTeleportToVarrock(this);
        stateTeleportToFalador = new StateTeleportToFalador(this);
        stateTeleportToPhasmatys = new StateTeleportToPhasmatys(this);
        stateDropWeeds = new StateDropWeeds(this);
        stateNoteHerbs = new StateNoteHerbs(this);
        stateRestock = new StateRestock(this, selectedCompost);
        stateDiseasedHerbs = new StateDiseasedHerbs(this);
        stateTeleportToTitheFarm = new StateTeleportToTitheFarm(this);
        stateWalkToHosidius = new StateWalkToHosidius(this);

    }

    @Override
    public int onLoop() throws InterruptedException {

        stateTeleportToArdougne.execute();
        stateDeadHerbs.execute();
        stateDiseasedHerbs.execute();
        stateHarvestHerbs.execute();
        statePlantHerbs.execute();
        stateCompostHerbs.execute();
        stateNoteHerbs.execute();
        stateDropWeeds.execute();

        stateTeleportToCamelot.execute();
        stateDeadHerbs.execute();
        stateDiseasedHerbs.execute();
        stateHarvestHerbs.execute();
        statePlantHerbs.execute();
        stateCompostHerbs.execute();
        stateNoteHerbs.execute();
        stateDropWeeds.execute();

        stateTeleportToFalador.execute();
        stateDeadHerbs.execute();
        stateDiseasedHerbs.execute();
        stateHarvestHerbs.execute();
        statePlantHerbs.execute();
        stateCompostHerbs.execute();
        stateNoteHerbs.execute();
        stateDropWeeds.execute();

        stateTeleportToPhasmatys.execute();
        stateDeadHerbs.execute();
        stateDiseasedHerbs.execute();
        stateHarvestHerbs.execute();
        statePlantHerbs.execute();
        stateCompostHerbs.execute();
        stateNoteHerbs.execute();
        stateDropWeeds.execute();
/*
        stateTeleportToTitheFarm.execute();
        stateWalkToHosidius.execute();
        stateDeadHerbs.execute();
        stateDiseasedHerbs.execute();
        stateHarvestHerbs.execute();
        statePlantHerbs.execute();
        stateCompostHerbs.execute();
        stateNoteHerbs.execute();
        stateDropWeeds.execute();


 */

        stateTeleportToVarrock.execute();
        stateRestock.execute();
        breakManager.activateBreakFor(90 * 60 * 1000);
        sleep(2500);


        return random(1000, 2000);
    }

    @Override
    public void onExit() {
        log("Stopping Simple Herb Farmer...");
    }
}
