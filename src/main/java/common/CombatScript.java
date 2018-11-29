package common;

import common.combat.CombatNode;
import common.eat.EatNode;
import common.eat.edible.EdibleItemListFactory;
import common.node.RootNode;
import common.node.general.WalkNode;
import common.node.graph.GraphNode;
import common.node.graph.GraphScript;
import common.npc.NpcModel;
import common.npc.monster.Goblin;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import static common.BuildConfig.BUILD_VERSION;

@ScriptManifest(
        author = "srMarlins",
        description = "Test kotlin",
        category = Category.COMBAT,
        version = BUILD_VERSION,
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
