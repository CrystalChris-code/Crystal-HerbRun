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
    private StateWalkToBirdhouseA stateWalkToBirdhouseA;
    private StateWalkToBirdhouseB stateWalkToBirdhouseB;
    private StateWalkToBirdhouseC stateWalkToBirdhouseC;
    private StateWalkToBirdhouseD stateWalkToBirdhouseD;
    private StateTeleportToVerdant stateTeleportToVerdant;
    private StateWalkToMushTree stateWalkToMushTree;
    private StateCreateBirdhouse stateCreateBirdhouse;
    private StatePlantBirdhouse statePlantBirdhouse;
    private StateSeedBirdhouse stateSeedBirdhouse;
    private StateHarvestBirdhouse stateHarvestBirdhouse;
    private StateTeleportToDigsite stateTeleportToDigsite;
    private StateRestockBirdhouse stateRestockBirdhouse;
    private StateTravelToFossilIsland stateTravelToFossilIsland;
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
        LogTypes selectedLogType = gui.getSelectedLogs();
        BirdhouseSeedTypes selectedSeed = gui.getSelectedSeed();

        log("Selected Seed: " + selectedHerb.getSeedName());
        log("Selected Compost: " + selectedCompost.getItemName());
        log("Selected Birdhouse: " + selectedLogType. getLogName());
        log("Selected Seed: " + selectedSeed.getBirdhouseSeedName());

        stateCompostHerbs = new StateCompostHerbs(this, selectedCompost);
        stateTeleportToVerdant = new StateTeleportToVerdant(this);
        stateWalkToBirdhouseA = new StateWalkToBirdhouseA(this);
        stateWalkToBirdhouseB = new StateWalkToBirdhouseB(this);
        stateWalkToBirdhouseC = new StateWalkToBirdhouseC(this);
        stateWalkToBirdhouseD = new StateWalkToBirdhouseD(this);
        stateWalkToMushTree = new StateWalkToMushTree(this);
        statePlantHerbs = new StatePlantHerbs(this, selectedHerb);
        stateCreateBirdhouse = new StateCreateBirdhouse(this, selectedLogType);
        stateSeedBirdhouse = new StateSeedBirdhouse(this,selectedSeed);
        statePlantBirdhouse = new StatePlantBirdhouse(this);
        stateHarvestBirdhouse = new StateHarvestBirdhouse(this);
        stateTeleportToDigsite = new StateTeleportToDigsite(this);
        stateRestockBirdhouse = new StateRestockBirdhouse(this,selectedSeed,selectedLogType);
        stateTravelToFossilIsland = new StateTravelToFossilIsland(this);
        stateHarvestHerbs = new StateHarvestHerbs(this);
        stateDeadHerbs = new StateDeadHerbs(this);
        stateTeleportToArdougne = new StateTeleportToArdougne(this);
        stateTeleportToCamelot = new StateTeleportToCamelot(this);
        stateTeleportToVarrock = new StateTeleportToVarrock(this);
        stateTeleportToFalador = new StateTeleportToFalador(this);
        stateTeleportToPhasmatys = new StateTeleportToPhasmatys(this);
        stateDropWeeds = new StateDropWeeds(this);
        stateNoteHerbs = new StateNoteHerbs(this);
        stateRestock = new StateRestock(this, selectedCompost, selectedHerb);
        stateDiseasedHerbs = new StateDiseasedHerbs(this);
        stateTeleportToTitheFarm = new StateTeleportToTitheFarm(this);

    }

    @Override
    public int onLoop() throws InterruptedException {
        //BIRDHOUSE RUNS START!
        stateRestockBirdhouse.execute();
        stateCreateBirdhouse.execute();
        stateTeleportToDigsite.execute();
        stateTravelToFossilIsland.execute();

        stateWalkToBirdhouseA.execute();
        stateHarvestBirdhouse.execute();
        statePlantBirdhouse.execute();
        stateSeedBirdhouse.execute();

        stateWalkToBirdhouseB.execute();
        stateHarvestBirdhouse.execute();
        statePlantBirdhouse.execute();
        stateSeedBirdhouse.execute();

        stateWalkToMushTree.execute();
        stateTeleportToVerdant.execute();
        stateWalkToBirdhouseC.execute();
        stateHarvestBirdhouse.execute();
        statePlantBirdhouse.execute();
        stateSeedBirdhouse.execute();

        stateWalkToBirdhouseD.execute();
        stateHarvestBirdhouse.execute();
        statePlantBirdhouse.execute();
        stateSeedBirdhouse.execute();

        stateTeleportToVarrock.execute();
        stateRestock.execute();

        stateTeleportToTitheFarm.execute();
        stateDeadHerbs.execute();
        stateDiseasedHerbs.execute();
        stateHarvestHerbs.execute();
        statePlantHerbs.execute();
        stateCompostHerbs.execute();
        stateNoteHerbs.execute();
        stateDropWeeds.execute();

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

        stateTeleportToVarrock.execute();
        breakManager.activateBreakFor(90 * 60 * 1000);
        sleep(2500);

        return random(1000, 2000);
    }

    @Override
    public void onExit() {
        log("Stopping Simple Herb Farmer...");
    }
}
