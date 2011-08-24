package com.xdev.bb.game.engine.listener

import java.awt.event.MouseEvent
import java.awt.Rectangle

/**
 * Created by xdev 25.08.11 at 1:17
 */

trait MouseHandler {
  def getBounds: Rectangle
  def mouseMoved(e: MouseEvent)
  def mouseClicked(e: MouseEvent)

  def handleMouseClick(e: MouseEvent) {
    if(getBounds.intersects(e.getPoint.getX, e.getPoint.getY, 1, 1)) {
      mouseClicked(e)
    }
  }
  def handleMouseMove(e: MouseEvent) {
    if(getBounds.intersects(e.getPoint.getX, e.getPoint.getY, 1, 1)) {
       mouseMoved(e)
    }
  }
}