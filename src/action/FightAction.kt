package action


import org.dreambot.api.methods.filter.Filter
import org.dreambot.api.methods.map.Area
import org.dreambot.api.script.AbstractScript
import org.dreambot.api.wrappers.interactive.NPC

open class FightAction(val script: AbstractScript, val area: Area, val npc: String) : BaseAction() {

    private val npcFilter = Filter<NPC> {
        it?.name == npc && it.isHealthBarVisible.not()
    }

    public override fun execute() {
        when {
            script.localPlayer.isInCombat -> {
            }
            area.contains(script.localPlayer) -> {
                script.npcs.closest(npcFilter)?.interact("Attack")
            }
            else -> {
                script.walking.walk(area.randomTile)
            }
        }
    }
}