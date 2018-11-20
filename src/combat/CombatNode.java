package combat;

import node.RootNode;
import npc.NpcModel;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.NPC;

public class CombatNode extends RootNode {

    private Filter<NPC> npcFilter;
    private NpcModel npcToFight;

    public CombatNode(NpcModel npcToFight) {
        this.npcToFight = npcToFight;
        this.npcFilter = npc -> this.npcToFight.name().equals(npc.getName()) && !npc.isHealthBarVisible();
    }

    @Override
    public boolean accept() {
        return !getLocalPlayer().isInCombat() && getFightArea().contains(getLocalPlayer());
    }

    @Override
    public int execute() {
        NPC closest = getNpcs().closest(npcFilter);
        if (closest == null) {
            getWalking().walk(getFightArea().getRandomTile());
            return super.execute();
        }

        toggleSpecialAttack();

        if (!getLocalPlayer().isInCombat()) {
            closest.interact("Attack");
            //MethodProvider.sleepUntil(() -> getLocalPlayer().isInCombat(),getMaxExecutionTime());
        }

        return super.execute();
    }

    @Override
    public int getMaxExecutionTime() {
        return 1500;
    }

    @Override
    protected int getMinExecutionTime() {
        return 1000;
    }


    private Area getFightArea() {
        return this.npcToFight.area();
    }

    private void toggleSpecialAttack() {
        if (getCombat().getSpecialPercentage() >= 50) getCombat().toggleSpecialAttack(true);
    }
}
