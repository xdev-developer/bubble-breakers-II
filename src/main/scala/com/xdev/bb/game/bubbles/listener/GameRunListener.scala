package com.xdev.bb.game.bubbles.listener

import com.xdev.bb.game.engine.listener.GameListener
import java.awt.event.{MouseEvent, KeyEvent}
import com.xdev.bb.game.engine.manager.ResourceManager
import com.xdev.bb.game.bubbles.controller.BubbleBreakersController
import java.awt.{Color, Graphics2D}
import com.xdev.bb.game.engine.utils.GameUtils._
import java.awt.image.BufferedImage
import util.Random
import com.xdev.bb.game.bubbles.entity.{Bubbles, Bubble}

/**
 * Created by xdev 18.08.11 at 3:16
 */

object GameRunListener extends GameListener {

  lazy val bubblesImages  = Map[Int, BufferedImage](0 -> ResourceManager.images("1.png"),
                                                      1 -> ResourceManager.images("2.png"),
                                                      2 -> ResourceManager.images("3.png"),
                                                      3 -> ResourceManager.images("4.png"),
                                                      4 -> ResourceManager.images("5.png"),
                                                      5 -> ResourceManager.images("6.png"),
                                                      6 -> ResourceManager.images("7.png"))

  val MIN_BUBBLES_IN_SELECTION = 2
  val BUBBLES_START_ROW = 4

  def init(g: Graphics2D, size: (Int, Int)) {
    val (w, h) = size
    Bubbles.columns = w / Bubble.size
    Bubbles.rows = h / Bubble.size
  }

  def update(delta: Double) {
   Bubbles.filter(!_.died).foreach(_.update(delta))
  }

  def render(g: Graphics2D, size: (Int, Int)) {
    hiQuality(g , () => {
      renderGreed(g)
      Bubbles.filter(!_.died).foreach(_.render(g))
    })
  }

  def  initGameObjects() {
    val rand = new Random(System.currentTimeMillis)
    val maxBubblesInLevel = if(BubbleBreakersController.currentLevel >= bubblesImages.size) {
      bubblesImages.size
    }else {
       BubbleBreakersController.currentLevel + 1
    }
    Bubbles.clear()
    for(y <- 0 until Bubbles.rows; x <- 0 until Bubbles.columns){
      val bubbleType = rand.nextInt(maxBubblesInLevel)
      val bubble = new Bubble(bubblesImages(bubbleType), (x * Bubble.size, y * Bubble.size), bubbleType)
      if(y < BUBBLES_START_ROW) bubble.died = true
      Bubbles += bubble
    }
  }

  /**
   * Greed rendering
   * @param g
   */
  private def renderGreed(g: Graphics2D) {
    val prevColor = g.getColor
    g.setColor(Color.GRAY)
    for(x <- 0 until Bubbles.columns; y <- 0 until Bubbles.rows){
      g.drawRect(x * Bubble.size, y * Bubble.size, Bubble.size, Bubble.size)
    }
    g.setColor(prevColor)
  }


  def keyPressed(e: KeyEvent) {
    if(e.getKeyCode == KeyEvent.VK_ESCAPE){
      BubbleBreakersController.showMenu()
    }
  }

  def mouseClicked(e: MouseEvent) {
    Bubbles.foreach(b => b.selected = false)  // deselect all bubbles
    Bubbles.foreach(_.handleMouseMove(e))
    val selectedBubbles = Bubbles.filter(_.selected)
    if(selectedBubbles.nonEmpty){
      val bubblesSelection = Bubbles.getBubblesSelectionPath(selectedBubbles.head)
      if(bubblesSelection.size >= MIN_BUBBLES_IN_SELECTION) {
        bubblesSelection.foreach(b => b.died = true)
      }
    }
  }

  def mouseMoved(e: MouseEvent) {
    Bubbles.foreach(b => b.selected = false)  // deselect all bubbles
    Bubbles.foreach(_.handleMouseMove(e))
    val selectedBubbles = Bubbles.filter(_.selected)
    if(selectedBubbles.nonEmpty){
     val bubblesSelection = Bubbles.getBubblesSelectionPath(selectedBubbles.head)
      if(bubblesSelection.size >= MIN_BUBBLES_IN_SELECTION) {
        bubblesSelection.foreach(b => b.selected = true)
      }
    }
  }
}
