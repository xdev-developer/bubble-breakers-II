package com.xdev.bb.game.engine.ui

import com.xdev.bb.game.engine.view.GameLoop
import javax.swing.{BorderFactory, JPanel, JFrame}
import java.awt.{Dimension, BorderLayout}
import com.xdev.bb.game.engine.controller.GameController

/**
 * Created by xdev 18.08.11 at 0:41
 */

class GameWindow(controller: GameController) extends JFrame{

  val screenView = new GameLoop(controller)

  def runGame() {
    screenView.run()
  }

  def createAndShowGUI(title: String, size: Dimension) {
    setTitle(title)
    setSize(size)
    setResizable(false)
    setLocationRelativeTo(null)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    val pane = new JPanel(new BorderLayout())
    pane.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7))
    pane.add(screenView, BorderLayout.CENTER)
    getContentPane.add(BorderLayout.CENTER, pane)

    setVisible(true)
    screenView.requestFocus()
  }
}