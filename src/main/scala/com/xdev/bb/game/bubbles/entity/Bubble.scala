package com.xdev.bb.game.bubbles.entity

import java.awt.image.BufferedImage
import java.awt.geom.AffineTransform
import java.awt.event.MouseEvent
import com.xdev.bb.game.engine.listener.MouseHandler
import java.awt.{Color, Rectangle, Graphics2D}

/**
 * Created by xdev 20.08.11 at 1:02
 */

case class Bubble(image: BufferedImage, pos: (Int, Int), bubbleType: Int) extends MouseHandler {
  var (x, y) = pos
  var selected = false
  var marked = false
  var died = false
  private var rotateAngle = 0.0
  private val theta = math.Pi / 180.0
  private val halfWidth, halfHeight = Bubble.size / 2
  private val boundingBox = new Rectangle()
  private val rotationSpeed = 700

  def update(delta: Double) {
    if(died) return
    rotateAngle += rotationSpeed * (delta/ 1000)
  }

  def render(g: Graphics2D){
    if(died) return
    boundingBox.setBounds(x, y, Bubble.size, Bubble.size)
    if(died){
      val defColor = g.getColor
      g.setColor(Color.RED)
      g.fillOval(x, y, 30, 30)
      g.setColor(defColor)
      return
    }
    if(selected) {
      val at: AffineTransform = new AffineTransform
      at.translate(x, y)
      at.rotate(rotateAngle * theta, halfWidth, halfHeight)
      g.drawImage(image, at, null)
    } else {
      g.drawImage(image, x, y, null)
      rotateAngle = 0.0f
    }
  }

  def mouseClicked(e: MouseEvent) {
    if(died) return
    marked = true
  }
  def mouseMoved(e: MouseEvent) {
    if(died) return
    marked = true
  }
  def getBounds: Rectangle = boundingBox

  def row: Int = y / Bubble.size
  def column: Int = x / Bubble.size
}

object Bubble {
  def size: Int = 30
}