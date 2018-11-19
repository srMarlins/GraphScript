import combat.CombatNode;
import node.RootNode;
import node.graph.GraphNode;
import node.graph.GraphScript;
import npc.Goblin;
import npc.NpcModel;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(
        author = "srMarlins",
        description = "Test kotlin",
        category = Category.COMBAT,
        version = 0.06,
        name = "Primitve Combat"
)
public class CombatScript extends GraphScript {


    private NpcModel goblin = new Goblin();
    private RootNode rootNode = new CombatNode(goblin);

    @Override
    public void onStart() {
        super.onStart();
        //addNodes(new EatNode(EdibleItemListFactory.availableEdibleMap.get(315), 20));
        //addNodes(new WalkNode(goblin.area()));
        //addNodes(new CombatNode(goblin));
    }

    @Override
    public GraphNode getRoot() {
        return rootNode;
    }

    @Override
    protected GraphNode buildGraph() {
        GraphNode root = getRoot();

        return root;
    }


}
