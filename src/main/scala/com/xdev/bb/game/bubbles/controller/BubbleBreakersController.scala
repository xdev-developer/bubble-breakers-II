package com.xdev.bb.game.bubbles.controller

import com.xdev.bb.game.engine.controller.GameController
import com.xdev.bb.game.engine.listener.GameListener
import com.xdev.bb.game.bubbles.GameState
import com.xdev.bb.game.bubbles.entity.Bubble
import com.xdev.bb.game.bubbles.listener._

/**
 * Created by xdev 18.08.11 at 3:15
 */

object BubbleBreakersController extends GameController {

  override def listeners = List(GameRunListener, GameMenuListener, GameLoadingListener, GameScoresListener)

  private object GameState {
      val GAME_RUN: Int = 1
      val GAME_MENU: Int = 2
      val GAME_SCORES: Int = 3
      val GAME_LOADING: Int = 4
  }
  private var gameState = GameState.GAME_MENU

  def gameListener(): Option[GameListener] = {
    gameState match {
      case GameState.GAME_RUN => Some(GameRunListener)
      case GameState.GAME_MENU => Some(GameMenuListener)
      case GameState.GAME_SCORES => Some(GameScoresListener)
      case GameState.GAME_LOADING => Some(GameLoadingListener)
      case _ => None
    }
  }

  def newGame() {
    gameState = GameState.GAME_LOADING
    Thread.sleep(1000) // For fun !
    GameRunListener.initGameObjects()
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

  def showMenu(){
    gameState = GameState.GAME_MENU
  }
}