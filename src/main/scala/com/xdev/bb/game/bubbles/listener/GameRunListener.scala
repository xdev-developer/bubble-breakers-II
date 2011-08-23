package com.xdev.bb.game.bubbles.listener

import com.xdev.bb.game.engine.listener.GameListener
import java.awt.event.{MouseEvent, KeyEvent}
import com.xdev.bb.game.bubbles.entity.Bubble
import java.awt.{Color, Graphics2D}

/**
 * Created by xdev 18.08.11 at 3:16
 */

object GameRunListener extends GameListener {

  var widthInGreed: Int = 0
  var heightInGreed: Int = 0

  def init(g: Graphics2D, size: (Int, Int)) {
    val (w, h) = size
    widthInGreed = w / Bubble.size
    heightInGreed = h / Bubble.size
  }

  def update(delta: Long) = null

  def render(g: Graphics2D, size: (Int, Int)) = {
    renderGreed(g)
  }

  /**
   * Greed rendering
   * @param g
   */
  private def renderGreed(g: Graphics2D): Unit = {
    val prevColor = g.getColor
    g.setColor(Color.GRAY)
    for(x <- 0 until widthInGreed; y <- 0 until heightInGreed){
      g.drawRect(x * Bubble.size, y * Bubble.size, Bubble.size, Bubble.size)
    }
    g.setColor(prevColor)
  }


  def keyPressed(e: KeyEvent) {}

  def mouseClicked(e: MouseEvent) {}

  def mouseMoved(e: MouseEvent) {}
  }
