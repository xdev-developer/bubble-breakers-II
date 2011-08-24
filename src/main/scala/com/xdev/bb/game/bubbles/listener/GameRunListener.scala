package com.xdev.bb.game.bubbles.listener

import com.xdev.bb.game.engine.listener.GameListener
import java.awt.event.{MouseEvent, KeyEvent}
import com.xdev.bb.game.engine.manager.ResourceManager
import com.xdev.bb.game.bubbles.entity.Bubble
import com.xdev.bb.game.bubbles.controller.BubbleBreakersController
import java.awt.{Color, Graphics2D}
import com.xdev.bb.game.engine.utils.GameUtils._
import java.awt.image.BufferedImage
import util.Random

/**
 * Created by xdev 18.08.11 at 3:16
 */

object GameRunListener extends GameListener {

  var widthInGreed: Int = 0
  var heightInGreed: Int = 0
  var bubbles: List[Bubble] = List()
  lazy val bubblesImages  = Map[Int, BufferedImage](0 -> ResourceManager.images("1.png"),
                                                      1 -> ResourceManager.images("2.png"),
                                                      2 -> ResourceManager.images("3.png"),
                                                      3 -> ResourceManager.images("4.png"),
                                                      4 -> ResourceManager.images("5.png"),
                                                      5 -> ResourceManager.images("6.png"),
                                                      6 -> ResourceManager.images("7.png"))

  def init(g: Graphics2D, size: (Int, Int)) {
    val (w, h) = size
    widthInGreed = w / Bubble.size
    heightInGreed = h / Bubble.size
  }

  def update(delta: Double) {
   bubbles.foreach(_.update(delta))
  }

  def render(g: Graphics2D, size: (Int, Int)) {
    hiQuality(g , () => {
      renderGreed(g)
      bubbles.foreach(_.render(g))
    })
  }

  def  initGameObjects() {
    val rand = new Random(System.currentTimeMillis)
    val maxBubblesInLevel = if(BubbleBreakersController.currentLevel >= bubblesImages.size) {
      bubblesImages.size
    }else {
       BubbleBreakersController.currentLevel + 1
    }

    bubbles = List()
    for(x <- 0 until widthInGreed; y <- 4 until heightInGreed){
      bubbles = new Bubble(bubblesImages(rand.nextInt(maxBubblesInLevel)), (x * Bubble.size, y * Bubble.size)) :: bubbles
    }
  }

  /**
   * Greed rendering
   * @param g
   */
  private def renderGreed(g: Graphics2D) {
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
