package com.xdev.bb.game.view

import java.awt.Graphics2D
import java.awt.event.{KeyEvent, MouseEvent}

/**
 * Created by xdev 18.08.11 at 1:13
 */

trait View {

  def update(delta: Long)
  def render(g: Graphics2D)

  def mouseMoved(e: MouseEvent)
  def mouseClicked(e: MouseEvent)
  def keyPressed(e: KeyEvent)
}