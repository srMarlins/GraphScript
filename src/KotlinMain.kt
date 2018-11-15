package main

import action.FightAction
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
    val fight = FightAction(this, GOBLIN_AREA, "Goblin")
    override fun onLoop(): Int {
        fight.execute()
        //walking.walk(GOBLIN_AREA.randomTile)
        return 1000
    }

    companion object {
        val GOBLIN_AREA = Area(3243, 3241, 3259, 3231)
    }
}