package com.xdev.bb.game.bubbles.listener

import com.xdev.bb.game.engine.listener.GameListener
import java.awt.event.{MouseEvent, KeyEvent}
import com.xdev.bb.game.engine.utils.GameUtils
import com.xdev.bb.game.bubbles.controller.BubbleBreakersController
import com.xdev.bb.game.engine.utils.GameUtils._
import java.awt.{Font, Graphics2D}

/**
 * Created by xdev 18.08.11 at 3:17
 */

object GameScoresListener extends GameListener {

  private val font: Font = new Font("Helvetica", Font.PLAIN, 16)

  def render(g: Graphics2D, size: (Int, Int)) {
    val (w, h) = size
    val defaultFont = g.getFont
    hiQuality(g , () => {
      g.setFont(font)
      val textComplete = "Level %s Completed !!!".format(BubbleBreakersController.currentLevel)
      val text = "Press <Enter> to continue of killing bubbles"
      g.drawString(textComplete, (w / 2) - (GameUtils.getStringSize(textComplete, g.getFont, g)._1 / 2), 100)
      g.drawString(text, (w / 2) - (GameUtils.getStringSize(text, g.getFont, g)._1 / 2), 120)
    })
    g.setFont(defaultFont)
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