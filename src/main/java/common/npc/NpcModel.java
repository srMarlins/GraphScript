package common.npc;

import org.jetbrains.annotations.NotNull;
import org.dreambot.api.methods.map.Area;

import java.util.List;

public interface NpcModel {
    @NotNull
    String name();

    @NotNull
    Area area();

    @NotNull
    List<Integer> getLootableItemIds();
}
