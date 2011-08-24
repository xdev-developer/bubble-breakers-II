package com.xdev.bb.game.bubbles.listener

import com.xdev.bb.game.engine.listener.GameListener
import java.awt.Graphics2D
import java.awt.event.{KeyEvent, MouseEvent}

/**
 * Created by xdev 24.08.11 at 2:03
 */

object GameLoadingListener extends GameListener {
  def render(g: Graphics2D, size: (Int, Int))  {
    val (w, h) = size
    g.drawString("Loading ...", w / 2, h / 2)
  }

  def update(delta: Long) = null
  def init(g: Graphics2D, size: (Int, Int)) = null

  def keyPressed(e: KeyEvent) = null
  def mouseClicked(e: MouseEvent) = null
  def mouseMoved(e: MouseEvent) = null


}