package com.xdev.bb.game.ui

import com.xdev.bb.game.view.GameViewScreen
import javax.swing.{BorderFactory, JPanel, JFrame}
import java.awt.{Dimension, BorderLayout}
import com.xdev.bb.game.controller.GameController

/**
 * Created by xdev 18.08.11 at 0:41
 */

class GameWindow(controller: GameController) extends JFrame{

  val screenView = new GameViewScreen(controller)

  def runGame() {
    screenView.run()
  }

  def createAndShowGUI(title: String, size: Dimension) {
    setTitle(title)
    setSize(size)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    val pane = new JPanel(new BorderLayout())
    pane.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7))
    pane.add(screenView, BorderLayout.CENTER)
    getContentPane.add(BorderLayout.CENTER, pane)

    setVisible(true)
    screenView.requestFocus()
  }
}