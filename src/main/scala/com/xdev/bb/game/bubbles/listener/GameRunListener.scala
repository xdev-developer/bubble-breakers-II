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

  var rows: Int = 0
  var columns: Int = 0
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
    columns = w / Bubble.size
    rows = h / Bubble.size
  }

  def update(delta: Double) {
   bubbles = bubbles.filterNot(_.died)
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
    for(y <- 0 until rows; x <- 0 until columns){
      val bubbleType = rand.nextInt(maxBubblesInLevel)
      bubbles = new Bubble(bubblesImages(bubbleType), (x * Bubble.size, y * Bubble.size), bubbleType) :: bubbles
    }
    bubbles = bubbles.reverse
  }

  /**
   * Greed rendering
   * @param g
   */
  private def renderGreed(g: Graphics2D) {
    val prevColor = g.getColor
    g.setColor(Color.GRAY)
    for(x <- 0 until columns; y <- 0 until rows){
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
    bubbles.foreach(b => b.marked = false) // unmark all bubbles
    bubbles.foreach(_.handleMouseClick(e))
    val markedBubbles = bubbles.filter(_.marked)
    if(markedBubbles.nonEmpty){
      val head = markedBubbles.head
      findBubblesPath(head)
    }
  }

  def findBubblesPath(head: Bubble) {
    println(head.bubbleType, head.row, head.column)
    val row = head.row
    val column = head.column
    val index = (row * columns) + column
    val b = bubbles(index)
    println(b.bubbleType, b.row, b.column)
  }

  def mouseMoved(e: MouseEvent) {
    bubbles.foreach(b => b.selected = false)  // deselect all bubbles
    bubbles.foreach(_.handleMouseMove(e))
  }
}
