package com.xdev.bb.game.bubbles.controller

import com.xdev.bb.game.engine.controller.GameController
import com.xdev.bb.game.engine.view.View
import com.xdev.bb.game.bubbles.GameState
import com.xdev.bb.game.bubbles.view.{GamScoresView, GameMenuView, GameRunView}

/**
 * Created by xdev 18.08.11 at 3:15
 */

object BubbleBreakersController extends GameController {

  var gameState = GameState.GAME_MENU

  def renderView(): Option[View] = {
    gameState match {
      case GameState.GAME_RUN => Some(GameRunView)
      case GameState.GAME_MENU => Some(GameMenuView)
      case GameState.GAME_SCORES => Some(GamScoresView)
      case _ => None
    }
  }
}