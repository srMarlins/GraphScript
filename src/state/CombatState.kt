package state

import org.dreambot.api.methods.map.Area

sealed class CombatState(val killArea: Area, val playerState: PlayerState) {
    class Fighting(killArea: Area, playerState: PlayerState) : CombatState(killArea, playerState)
    class Searching(killArea: Area, playerState: PlayerState) : CombatState(killArea, playerState)
    class Healing(killArea: Area, playerState: PlayerState) : CombatState(killArea, playerState)
    class Equiping(killArea: Area, playerState: PlayerState) : CombatState(killArea, playerState)
}