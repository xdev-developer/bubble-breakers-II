package com.xdev.bb.game.bubbles.listener

import com.xdev.bb.game.engine.listener.GameListener
import java.awt.Graphics2D
import java.awt.event.{MouseEvent, KeyEvent}
import com.xdev.bb.game.engine.utils.GameUtils
import com.xdev.bb.game.bubbles.controller.BubbleBreakersController

/**
 * Created by xdev 18.08.11 at 3:17
 */

object GameScoresListener extends GameListener {

  def render(g: Graphics2D, size: (Int, Int)) {
    val (w, h) = size
    val text = "Level %s Completed !!! Press <Enter> to continue of killing bubbles".format(BubbleBreakersController.currentLevel)
    g.drawString(text, (w / 2) - (GameUtils.getStringSize(text, g.getFont, g)._1 / 2), h / 2)

  }
  def init(g: Graphics2D, size: (Int, Int)) {}
  def update(delta: Double) {}
  def keyPressed(e: KeyEvent) {
    if(e.getKeyCode == KeyEvent.VK_ESCAPE){
      BubbleBreakersController.showMenu()
    }
    if(e.getKeyCode == KeyEvent.VK_ENTER){
      BubbleBreakersController.createNewLevel()
    }
  }
  def mouseClicked(e: MouseEvent) {}
  def mouseMoved(e: MouseEvent) {}
  }