package com.xdev.bb.game.engine.listener

import java.awt.Graphics2D
import java.awt.event.{KeyEvent, MouseEvent}

/**
 * Created by xdev 18.08.11 at 1:13
 */

abstract class GameListener {
  private var isInitialized: Boolean = false

  private [listener] def initListener(g: Graphics2D, size: (Int, Int)) {
    if (!isInitialized){
      init(g, size)
    }
    isInitialized = true
  }

  def init(g: Graphics2D, size: (Int, Int))
  def update(delta: Double)
  def render(g: Graphics2D, size: (Int, Int))

  private [listener] def  handleMouseMoved(e: MouseEvent){
    if(isInitialized) {mouseMoved(e)}
  }
  def mouseMoved(e: MouseEvent)

  private [listener] def handleMouseClicked(e: MouseEvent) {
    if (isInitialized){mouseClicked(e)}
  }
  def mouseClicked(e: MouseEvent)

  def keyPressed(e: KeyEvent)
}