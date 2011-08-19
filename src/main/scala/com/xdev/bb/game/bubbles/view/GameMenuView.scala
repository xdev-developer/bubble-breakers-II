package com.xdev.bb.game.bubbles.view

import com.xdev.bb.game.engine.view.View
import java.awt.event.{MouseEvent, KeyEvent}
import java.awt.Graphics2D
import com.xdev.bb.game.engine.ui.menu.{MenuItem, MenuBar}

/**
 * Created by xdev 18.08.11 at 3:16
 */

object GameMenuView extends View {

  private val newGame = new MenuItem("New Game", () => {println("NewGame")})
  private val continueGame = new MenuItem("Continue Game", () => {println("Continue Game")})
  private val restartGame = new MenuItem("Restart Game", () => {println("Restart Game")})
  private val exitGame = new MenuItem("Exit Game", () => {println("Exit Game")})

  private val menuBar = new MenuBar(List(newGame, continueGame, restartGame, exitGame))

  def render(g: Graphics2D, size: (Int, Int)) {menuBar.render(g, size)}
  def mouseClicked(e: MouseEvent) { menuBar.processMouseClick(e)}
  def mouseMoved(e: MouseEvent) { menuBar.processMouseMove(e)}
  def keyPressed(e: KeyEvent) {}
  def init(g: Graphics2D, size: (Int, Int)) {}
  def update(delta: Long) {}
}