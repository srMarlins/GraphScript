package npc;

import org.dreambot.api.methods.map.Area;

import java.util.ArrayList;
import java.util.List;

public class Goblin implements NpcModel {
    private static Area GOBLIN_AREA = new Area(3243, 3241, 3259, 3231);

    @Override
    public String name() {
        return "Goblin";
    }

    @Override
    public Area area() {
        return GOBLIN_AREA;
    }

    @Override
    public List<Integer> getLootableItemIds() {
        return new ArrayList<>();
    }
}
