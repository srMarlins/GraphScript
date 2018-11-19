package combat;

import node.RootNode;
import npc.NpcModel;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.NPC;

public class CombatNode extends RootNode {

    private Filter<NPC> npcFilter;
    private NpcModel npcToFight;

    public CombatNode(NpcModel npcToFight) {
        this.npcToFight = npcToFight;
        this.npcFilter = npc -> this.npcToFight.name().equals(npc.getName()) && !npc.isHealthBarVisible();
        getCombat().toggleAutoRetaliate(false);
    }

    @Override
    public boolean accept() {
        log("Combat: accept");
        return !getLocalPlayer().isInCombat() && getFightArea().contains(getLocalPlayer());
    }

    @Override
    public int execute() {
        log("Combat: execute");
        NPC closest = getNpcs().closest(npcFilter);
        if (closest == null) {
            log("Combat: Walking");
            getWalking().walk(getFightArea().getRandomTile());
            return super.execute();
        }

        toggleSpecialAttack();

        if (!getLocalPlayer().isInCombat()) {
            log("Combat: attack");
            closest.interact("Attack");
        }

        return super.execute();
    }

    @Override
    public int getMaxExecutionTime() {
        return 900;
    }

    @Override
    protected int getMinExecutionTime() {
        return 100;
    }


    private Area getFightArea() {
        return this.npcToFight.area();
    }

    private void toggleSpecialAttack() {
        if (getCombat().getSpecialPercentage() >= 50) getCombat().toggleSpecialAttack(true);
    }
}
