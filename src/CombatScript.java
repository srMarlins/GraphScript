
import eat.EatTask;
import eat.edible.EdibleItemListFactory;
import general.BankTask;
import npc.Goblin;
import npc.NpcModel;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;
import combat.CombatTask;
import general.WalkTask;

@ScriptManifest(
        author = "srMarlins",
        description = "Test kotlin",
        category = Category.COMBAT,
        version = 0.06,
        name = "Primitve Combat"
)
public class CombatScript extends TaskScript {


    private NpcModel goblin = new Goblin();

    @Override
    public void onStart() {
        super.onStart();
        //addNodes(new EatTask(EdibleItemListFactory.availableEdibleMap.get(315), 20));
        addNodes(new WalkTask(goblin.area()));
        addNodes(new CombatTask(goblin));
    }



}
