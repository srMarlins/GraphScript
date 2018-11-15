package state

import org.dreambot.api.methods.map.Area

sealed class PlayerState {
    data class LoggedIn(val world: Int) : PlayerState()
    data class LoggedOut(val previousWorld: Int) : PlayerState()
    data class EnteredCombat(val combatState: CombatState) : PlayerState()
    data class ExitedCombat(val combatState: CombatState) : PlayerState()
    data class Traveling(val to: Area) : PlayerState()
    data class LowHealth(val health: Int) : PlayerState()
}