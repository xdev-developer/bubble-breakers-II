package com.xdev.bb.game.engine.ui.menu

import java.awt.event.MouseEvent
import java.awt.{Font, Graphics2D}
import com.xdev.bb.game.engine.utils.GameUtils

/**
 * Created by xdev 19.08.11 at 2:19
 */

class MenuBar(val menus: List[MenuItem], val y: Int = 100, val yPadding: Int = 5, var font: Font = new Font("Helvetica", Font.PLAIN, 16)) {

  private var isInitialized = false

  private def initMenuItems(g: Graphics2D, containerSize: (Int, Int)){
    //Calculate menu item Y position
    val size: (Int, Int) = GameUtils.getStringSize("Simple Text", font, g)
    val m: List[(MenuItem, Int)] = menus.zipWithIndex
    for((menu, index) <- m){
      menu.font = font
      menu.y = (y + (index * (size._2 + yPadding)))
    }
  }

  def render(g: Graphics2D, containerSize: (Int, Int)){
    if(!isInitialized){
      initMenuItems(g, containerSize)
      isInitialized = true
    }
    menus.foreach(_.render(g ,containerSize))
  }

  def processMouseClick(e: MouseEvent){
    menus.foreach(_.processMouseClick(e))
  }

  def processMouseMove(e: MouseEvent){
    menus.foreach(_.processMouseMove(e))
  }
}
