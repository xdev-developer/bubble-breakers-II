package com.xdev.bb.game.bubbles.view

import com.xdev.bb.game.engine.view.View
import java.awt.Graphics2D
import java.awt.event.{MouseEvent, KeyEvent}
import com.xdev.bb.game.bubbles.controller.BubbleBreakersController
import com.xdev.bb.game.bubbles.GameState

/**
 * Created by xdev 18.08.11 at 3:16
 */

object GameRunView extends View {

    def init(g: Graphics2D, size: (Int, Int)) = null

    def update(delta: Long) = null

    def render(g: Graphics2D, size: (Int, Int)) = {
       g.drawOval(10, 10, 30, 30)
    }

    def keyPressed(e: KeyEvent) = null

    def mouseClicked(e: MouseEvent) = {
      BubbleBreakersController.gameState = GameState.GAME_MENU
    }

    def mouseMoved(e: MouseEvent) = null
  }
