package com.xdev.bb.game.bubbles.listener

import com.xdev.bb.game.engine.listener.GameListener
import java.awt.event.{MouseEvent, KeyEvent}
import com.xdev.bb.game.engine.manager.ResourceManager
import com.xdev.bb.game.bubbles.entity.Bubble
import com.xdev.bb.game.bubbles.controller.BubbleBreakersController
import java.awt.{Color, Graphics2D}
import com.xdev.bb.game.engine.utils.GameUtils._

/**
 * Created by xdev 18.08.11 at 3:16
 */

object GameRunListener extends GameListener {

  var widthInGreed: Int = 0
  var heightInGreed: Int = 0
  var bubbles: List[Bubble] = List()

  def init(g: Graphics2D, size: (Int, Int)) {
    val (w, h) = size
    widthInGreed = w / Bubble.size
    heightInGreed = h / Bubble.size
  }

  def update(delta: Long) = null

  def render(g: Graphics2D, size: (Int, Int)) {
    hiQuality(g , () => {
      renderGreed(g)
      bubbles.foreach(_.render(g))
    })
  }

  def  initGameObjects() {
    bubbles = List()
    for(x <- 0 until widthInGreed; y <- 0 until heightInGreed){
      bubbles = new Bubble(ResourceManager.images("1.png"), (x * Bubble.size, y * Bubble.size)) :: bubbles
    }
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


  def keyPressed(e: KeyEvent) {
    if(e.getKeyCode == KeyEvent.VK_ESCAPE){
      BubbleBreakersController.showMenu()
    }
  }

  def mouseClicked(e: MouseEvent) {}

  def mouseMoved(e: MouseEvent) {}
  }
