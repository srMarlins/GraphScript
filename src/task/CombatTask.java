package task;

import npc.NpcModel;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.NPC;
import util.PriorityMatrix;

public class CombatTask extends RandomTaskNode {


    private Filter<NPC> npcFilter;
    private NpcModel npcToFight;
    private PriorityMatrix priorityMatrix = new PriorityMatrix(PriorityMatrix.Priority.HIGH, PriorityMatrix.Priority.MED, PriorityMatrix.Priority.HIGH);

    public CombatTask(NpcModel npcToFight) {
        this.npcToFight = npcToFight;
        this.npcFilter = npc -> this.npcToFight.name().equals(npc.getName()) && !npc.isHealthBarVisible();
    }


    @Override
    public int priority() {
        return priorityMatrix.getPriorityValue();
    }

    @Override
    public boolean accept() {
        return !getLocalPlayer().isInCombat() && getFightArea().contains(getLocalPlayer());
    }

    @Override
    public int execute() {
        log("Combat: execute");
        NPC closest = getNpcs().closest(npcFilter);
        if (closest == null) {
            log("Combat: Walking");
            getWalking().walk(getFightArea().getRandomTile());
        } else if(!getLocalPlayer().isInCombat()) {
            log("Combat: attack");
            closest.interact("Attack");
        }

        return super.execute();
    }

    @Override
    int getMaxExecutionTime() {
        return 900;
    }

    @Override
    int getMinExecutionTime() {
        return 100;
    }

    private Area getFightArea() {
        return this.npcToFight.area();
    }
}
