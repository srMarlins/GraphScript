
import npc.Goblin;
import npc.NpcModel;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;
import task.CombatTask;
import task.WalkNode;

@ScriptManifest(
        author = "srMarlins",
        description = "Test kotlin",
        category = Category.COMBAT,
        version = 0.05,
        name = "Primitve Combat"
)
public class CombatScript extends TaskScript {


    private NpcModel goblin = new Goblin();

    @Override
    public void onStart() {
        super.onStart();
        addNodes(new WalkNode(goblin.area()));
        addNodes(new CombatTask(goblin));
    }



}
