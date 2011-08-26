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
    Bubbles.init(math.round(h / Bubble.size).toInt, math.round(w / Bubble.size).toInt)
  }

  def update(delta: Double) {
   Bubbles.filter(!_.died).foreach(_.update(delta))
   updateBubblesPositions()
   if(Bubbles.filter(!_.died).isEmpty) {
     BubbleBreakersController.levelComplected()
   }
  }

  def updateBubblesPositions(){
    for(c <- 0 until Bubbles.columns; r <- 0 until Bubbles.rows){
      val nextOpt = Bubbles.getBubble(r + 1, c)
      if(nextOpt.isDefined && nextOpt.get.died){
          val next = nextOpt.get
          val currentOpt = Bubbles.getBubble(r, c)
          if(currentOpt.isDefined){
            val current = currentOpt.get
            val pos : (Int, Int) = (current.x, current.y)
            current.x = next.x
            current.y = next.y
            next.x = pos._1
            next.y = pos._2
            Bubbles.set(r, c, next)
            Bubbles.set(r + 1, c, current)
          }
      }
    }

    for(c <- 0 until Bubbles.columns){
      val currentOpt = Bubbles.getBubble(Bubbles.rows - 1, c)
      if(currentOpt.isDefined && currentOpt.get.died){ // We found empty column
        for(r <- 0 until Bubbles.rows){
          val rightBubbleOpt = Bubbles.getBubble(r, c + 1)
          if(rightBubbleOpt.isDefined){
            val leftBubbleOpt = Bubbles.getBubble(r, c)
            if(leftBubbleOpt.isDefined){
              val left = leftBubbleOpt.get
              val right = rightBubbleOpt.get
              val pos : (Int, Int) = (left.x, left.y)
              left.x = right.x
              left.y = right.y
              right.x = pos._1
              right.y = pos._2
              Bubbles.set(r, c + 1, left)
              Bubbles.set(r, c, right)
            }
          }
        }
      }
    }
  }

  def render(g: Graphics2D, size: (Int, Int)) {
    hiQuality(g , () => {
      renderGreed(g)
      //Bubbles.filter(!_.died).foreach(_.render(g))
      Bubbles.foreach(_.render(g))
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
    for(r <- 0 until Bubbles.rows; c <- 0 until Bubbles.columns){
      val bubbleType = rand.nextInt(maxBubblesInLevel)
      val bubble = new Bubble(bubblesImages(bubbleType), (c * Bubble.size, r * Bubble.size), bubbleType)
      if(r < BUBBLES_START_ROW) bubble.died = true
      Bubbles.set(r, c, bubble)
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
    Bubbles.foreach(b => {b.selected = false; b.marked = false})  // deselect all bubbles
    Bubbles.foreach(_.handleMouseMove(e))
    val selectedBubbles = Bubbles.filter(_.marked)
    if(selectedBubbles.nonEmpty){
      val bubblesSelection = Bubbles.getBubblesSelectionPath(selectedBubbles.head)
      if(bubblesSelection.size >= MIN_BUBBLES_IN_SELECTION) {
        bubblesSelection.foreach(b => b.died = true)
      }
    }
  }

  def mouseMoved(e: MouseEvent) {
    Bubbles.foreach(b => {b.selected = false; b.marked = false})  // deselect all bubbles
    Bubbles.foreach(_.handleMouseMove(e))
    val selectedBubbles = Bubbles.filter(_.marked)
    if(selectedBubbles.nonEmpty){
     val bubblesSelection = Bubbles.getBubblesSelectionPath(selectedBubbles.head)
      if(bubblesSelection.size >= MIN_BUBBLES_IN_SELECTION) {
        bubblesSelection.foreach(b => b.selected = true)
      }
    }
  }
}
