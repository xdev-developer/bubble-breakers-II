package com.xdev.bb.game.bubbles.listener

import com.xdev.bb.game.engine.listener.GameListener
import java.awt.Graphics2D
import java.awt.event.{KeyEvent, MouseEvent}
import com.xdev.bb.game.engine.utils.GameUtils

/**
 * Created by xdev 24.08.11 at 2:03
 */
object GameLoadingListener extends GameListener {

  private val TEXT = "Loading ..."

  def render(g: Graphics2D, size: (Int, Int))  {
    val (w, h) = size
    g.drawString(TEXT, (w / 2) - (GameUtils.getStringSize(TEXT, g.getFont, g)._1 / 2), h / 2)
  }

  def update(delta: Double) {}
  def init(g: Graphics2D, size: (Int, Int)) {}
  def keyPressed(e: KeyEvent) {}
  def mouseClicked(e: MouseEvent) {}
  def mouseMoved(e: MouseEvent) {}
}