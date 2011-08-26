package com.xdev.bb.game.bubbles.controller

import com.xdev.bb.game.engine.controller.GameController
import com.xdev.bb.game.engine.listener.GameListener
import com.xdev.bb.game.bubbles.listener._

/**
 * Created by xdev 18.08.11 at 3:15
 */

object BubbleBreakersController extends GameController {

  override def listeners = List(GameRunListener, GameMenuListener, GameLoadingListener, GameScoresListener)

  var currentLevel = 1
  private var gameCreated = false

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
    gameCreated = createGame(1)
  }

  def createNewLevel(){
    gameCreated = createGame(currentLevel + 1)
  }

  def levelComplected(){
    gameState = GameState.GAME_SCORES
  }

  def continueGame() {
    if(gameCreated)
      gameState = GameState.GAME_RUN
  }

  def restartGame() {
    gameCreated = createGame(currentLevel)
  }

  def showMenu(){
    gameState = GameState.GAME_MENU
  }

  def exitGame() {
    sys.exit(0)
  }

  private def createGame(level: Int): Boolean = {
    gameState = GameState.GAME_LOADING
    currentLevel = level
    GameRunListener.initGameObjects()
    Thread.sleep(100) // For fun !
    gameState = GameState.GAME_RUN
    return true
  }
}
