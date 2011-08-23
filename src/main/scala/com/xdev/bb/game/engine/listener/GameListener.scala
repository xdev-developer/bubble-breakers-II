package com.xdev.bb.game.engine.listener

import java.awt.Graphics2D
import java.awt.event.{KeyEvent, MouseEvent}

/**
 * Created by xdev 18.08.11 at 1:13
 */

abstract class GameListener {
  var isInitialized: Boolean = false
  def init(g: Graphics2D, size: (Int, Int))
  def update(delta: Long)
  def render(g: Graphics2D, size: (Int, Int))

  def mouseMoved(e: MouseEvent)
  def mouseClicked(e: MouseEvent)
  def keyPressed(e: KeyEvent)
}