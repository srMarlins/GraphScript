package player


import org.dreambot.api.methods.map.Area
import state.PlayerState

class Player(private val localPlayer: Player) {


    init {

    }

    fun dispose() {

    }
    private fun handlePlayerState(playerState: PlayerState) {
        when (playerState) {
            is PlayerState.LoggedIn -> TODO()
            is PlayerState.LoggedOut -> TODO()
            is PlayerState.EnteredCombat -> TODO()
            is PlayerState.Traveling -> TODO()
            is PlayerState.LowHealth -> TODO()
            else -> TODO()
        }
    }

    private fun loggedIn(loggedIn: PlayerState.LoggedIn) {

    }

    private fun loggedOut(loggedOut: PlayerState.LoggedOut) {

    }

    private fun enteredCombat(enteredCombat: PlayerState.EnteredCombat) {}

    private fun exitedCombat() {}

    private fun traveling(traveling: PlayerState.Traveling) {

    }

    private fun lowHealth(lowHealth: PlayerState.LowHealth) {}


    sealed class PlayerEvent(val area: Area) {



        abstract fun performAction()
    }
}