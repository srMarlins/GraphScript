import combat.CombatNode;
import eat.EatNode;
import node.Registrar;
import node.RootNode;
import node.general.WalkNode;
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
        version = 0.07,
        name = "Primitive Combat"
)
public class CombatScript extends GraphScript {


    private NpcModel goblin = new Goblin();
    private RootNode rootNode = new CombatNode(goblin);

    @Override
    public void onStart(final String... strings) {
        log("onStart");
        super.onStart(strings);
    }

    @Override
    public void onStart() {
        log("start");
        super.onStart();
        //addNodes(new EatNode(EdibleItemListFactory.availableEdibleMap.get(315), 20));
        //addNodes(new WalkNode(goblin.area()));
        //addNodes(new CombatNode(goblin));
    }

    @Override
    public int onLoop() {
        log("loop");
        return super.onLoop();
    }

    @Override
    public GraphNode getRoot() {
        return rootNode;
    }

    @Override
    protected GraphNode buildGraph() {
        GraphNode root = getRoot();
        root.addNode(new WalkNode(goblin.area()), this);
        return root;
    }
}
