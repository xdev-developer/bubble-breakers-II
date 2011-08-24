package com.xdev.bb.game.bubbles.listener

import com.xdev.bb.game.engine.listener.GameListener
import java.awt.event.{MouseEvent, KeyEvent}
import java.awt.Graphics2D
import com.xdev.bb.game.engine.ui.menu.{MenuItem, MenuBar}
import com.xdev.bb.game.bubbles.controller.BubbleBreakersController

/**
 * Created by xdev 18.08.11 at 3:16
 */

object GameMenuListener extends GameListener {

  private val newGame = new MenuItem("New Game", () => BubbleBreakersController.newGame())
  private val continueGame = new MenuItem("Continue Game", () => BubbleBreakersController.continueGame())
  private val restartGame = new MenuItem("Restart Game", () => BubbleBreakersController.restartGame())
  private val exitGame = new MenuItem("Exit Game", () => BubbleBreakersController.exitGame())

  private val menuBar = new MenuBar(List(newGame, continueGame, restartGame, exitGame))

  def render(g: Graphics2D, size: (Int, Int)) {menuBar.render(g, size)}
  def mouseClicked(e: MouseEvent) { menuBar.processMouseClick(e)}
  def mouseMoved(e: MouseEvent) { menuBar.processMouseMove(e)}
  def keyPressed(e: KeyEvent) {
    if (e.getKeyCode == KeyEvent.VK_ESCAPE) BubbleBreakersController.exitGame()
  }
  def init(g: Graphics2D, size: (Int, Int)) {}
  def update(delta: Double) {}
}