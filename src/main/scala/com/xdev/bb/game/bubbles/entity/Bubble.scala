package com.xdev.bb.game.bubbles.entity

import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.geom.AffineTransform

/**
 * Created by xdev 20.08.11 at 1:02
 */

case class Bubble(image: BufferedImage, pos: (Int, Int)) {
  var (x, y) = pos
  var selected = true
  var rotateAngle = 0.0
  val theta = math.Pi / 180.0
  val halfWidth, halfHeight = Bubble.size / 2

  def update(delta: Double) {
    rotateAngle += (700 * delta) / 1000
  }

  def render(g: Graphics2D){
    if(selected) {
      val at: AffineTransform = new AffineTransform
      at.translate(x, y)
      at.rotate(rotateAngle * theta, halfWidth, halfHeight)
      g.drawImage(image, at, null)
      //selected = false
    } else {
      g.drawImage(image, x, y, null)
      rotateAngle = 0.0f
    }

  }

}

object Bubble {
  def size: Int = 30
}