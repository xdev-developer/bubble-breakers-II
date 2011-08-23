package com.xdev.bb.game.bubbles.controller

import com.xdev.bb.game.engine.controller.GameController
import com.xdev.bb.game.engine.listener.GameListener
import com.xdev.bb.game.bubbles.GameState
import com.xdev.bb.game.bubbles.view.{GamScoresListener, GameMenuListener, GameRunListener}
import com.xdev.bb.game.bubbles.entity.Bubble

/**
 * Created by xdev 18.08.11 at 3:15
 */

object BubbleBreakersController extends GameController {

  var gameState = GameState.GAME_MENU

  def gameListener(): Option[GameListener] = {
    gameState match {
      case GameState.GAME_RUN => Some(GameRunListener)
      case GameState.GAME_MENU => Some(GameMenuListener)
      case GameState.GAME_SCORES => Some(GamScoresListener)
      case _ => None
    }
  }

  def newGame() {
    gameState = GameState.GAME_RUN
  }

  def continueGame() {
    gameState = GameState.GAME_RUN
  }

  def restartGame() {
    gameState = GameState.GAME_RUN
  }

  def exitGame() {
    sys.exit(0)
  }
}