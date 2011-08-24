package com.xdev.bb.game.bubbles.entity

import java.awt.image.BufferedImage
import java.awt.Graphics2D

/**
 * Created by xdev 20.08.11 at 1:02
 */

case class Bubble(image: BufferedImage, pos: (Int, Int)) {

  def render(g: Graphics2D){
    g.drawImage(image, pos._1, pos._2, Bubble.size, Bubble.size, null)
  }

}

object Bubble {
  def size: Int = 30
}