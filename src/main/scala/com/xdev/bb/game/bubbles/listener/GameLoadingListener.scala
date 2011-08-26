package com.xdev.bb.game.bubbles.listener

import com.xdev.bb.game.engine.listener.GameListener
import java.awt.event.{KeyEvent, MouseEvent}
import com.xdev.bb.game.engine.utils.GameUtils
import java.awt.{Font, Graphics2D}
import com.xdev.bb.game.engine.utils.GameUtils._

/**
 * Created by xdev 24.08.11 at 2:03
 */
object GameLoadingListener extends GameListener {

  private val TEXT = "Loading ..."
  private val font: Font = new Font("Helvetica", Font.PLAIN, 16)

  def render(g: Graphics2D, size: (Int, Int))  {
    val (w, h) = size
    val defaultFont = g.getFont
    hiQuality(g , () => {
      g.setFont(font)
      g.drawString(TEXT, (w / 2) - (GameUtils.getStringSize(TEXT, g.getFont, g)._1 / 2), h / 2)
    })
    g.setFont(defaultFont)
  }

  def update(delta: Double) {}
  def init(g: Graphics2D, size: (Int, Int)) {}
  def keyPressed(e: KeyEvent) {}
  def mouseClicked(e: MouseEvent) {}
  def mouseMoved(e: MouseEvent) {}
}