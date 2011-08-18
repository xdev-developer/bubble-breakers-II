package com.xdev.bb.game.bubbles

import com.xdev.bb.game.ui.GameWindow
import controller.BubbleBreakersController
import java.awt.Dimension

/**
 * Created by xdev 18.08.11 at 3:14
 */

object BubbleBreakers {
  def main(args: Array[String]){
    val window = new GameWindow(BubbleBreakersController)
    window.createAndShowGUI(Configuration.title, new Dimension(Configuration.width, Configuration.height))
    window.runGame()
  }
}