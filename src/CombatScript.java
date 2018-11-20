import combat.CombatNode;
import eat.EatNode;
import eat.edible.EdibleItemListFactory;
import node.RootNode;
import node.general.WalkNode;
import node.graph.GraphNode;
import node.graph.GraphScript;
import npc.monster.Goblin;
import npc.NpcModel;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(
        author = "srMarlins",
        description = "Test kotlin",
        category = Category.COMBAT,
        version = 0.07,
        name = "Primitive Combat"
)
public class CombatScript extends GraphScript {

    private NpcModel goblin = new Goblin();
    private RootNode rootNode = new CombatNode(goblin);

    @Override
    public GraphNode getRoot() {
        return rootNode;
    }

    @Override
    protected GraphNode buildGraph() {
        GraphNode root = getRoot();
        root.addNode(new WalkNode(goblin.area()), this);
        root.addNode(new EatNode(EdibleItemListFactory.getAllEdibles().get(0), 20), this);
        return root;
    }
}
