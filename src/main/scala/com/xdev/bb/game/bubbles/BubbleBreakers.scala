package com.xdev.bb.game.bubbles

import com.xdev.bb.game.engine.ui.GameWindow
import controller.BubbleBreakersController
import java.awt.Dimension
import com.xdev.bb.game.engine.manager.ResourceManager

/**
 * Created by xdev 18.08.11 at 3:14
 */

object BubbleBreakers {
  def main(args: Array[String]){
    System.setProperty("sun.java2d.opengl", "true")
    ResourceManager.loadImages("/images/")
    val window = new GameWindow(BubbleBreakersController)
    window.createAndShowGUI(Configuration.title, new Dimension(Configuration.width, Configuration.height))
    window.runGame()
  }
}