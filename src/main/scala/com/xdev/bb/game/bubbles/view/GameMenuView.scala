package com.xdev.bb.game.bubbles.view

import com.xdev.bb.game.engine.view.View
import java.awt.Graphics2D
import java.awt.event.{MouseEvent, KeyEvent}
import com.xdev.bb.game.bubbles.controller.BubbleBreakersController
import com.xdev.bb.game.bubbles.{Configuration, GameState}
import com.xdev.bb.game.engine.utils.FPSCounter

/**
 * Created by xdev 18.08.11 at 3:16
 */

object GameMenuView extends View {

    def update(delta: Long) = null

    def render(g: Graphics2D, w: Double, h: Double) = {
      g.clearRect(0, 0, w.toInt, h.toInt)
      MenuBar.menus.foreach(_.render(g))
    }

    def keyPressed(e: KeyEvent) = null

    def mouseClicked(e: MouseEvent) = {
      BubbleBreakersController.gameState = GameState.GAME_RUN
    }

    def mouseMoved(e: MouseEvent) = null

  object MenuBar {
     val menus = List(
       new MenuItem("Hello", () => {println("hello")})
    )
  }

  class MenuItem(text: String, action: () => Unit){
    def render(g: Graphics2D) {
       g.drawString(text, 10, 10)
    }
  }
}