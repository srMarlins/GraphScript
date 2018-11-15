import org.dreambot.api.methods.map.Area
import org.dreambot.api.script.AbstractScript
import org.dreambot.api.script.Category
import org.dreambot.api.script.ScriptManifest

@ScriptManifest(
        author = "srMarlins",
        description = "Test kotlin",
        category = Category.COMBAT,
        version = 0.01,
        name = "Primitve Combat"
)

class KotlinMain : AbstractScript() {
    override fun onLoop(): Int {
        val area = Area(3243, 3241, 3259, 3231)
        walking.walk(area.randomTile)
        return 1000
    }
}