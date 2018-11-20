package eat.edible;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdibleItemListFactory {

    public static Map<Integer, Edible> availableEdibleMap = getAllEdibleMap();

    public static List<Edible> getAllEdibles() {
        ArrayList<Edible> list = new ArrayList<>();
        list.add(EdibleFactory.newEatEdible(315, "Shrimp"));
        return list;
    }

    public static Map<Integer, Edible> getAllEdibleMap() {
        Map<Integer, Edible> map = new HashMap<>();
        getAllEdibles().forEach(edible -> map.put(edible.id(), edible));
        return map;
    }
}
