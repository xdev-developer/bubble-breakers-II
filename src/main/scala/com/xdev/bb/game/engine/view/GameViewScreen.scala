package com.xdev.bb.game.view

import com.xdev.bb.game.controller.GameController
import collection.mutable.ArrayBuffer
import java.awt.image.BufferStrategy
import java.awt.{Graphics2D, Color, Canvas}
import java.awt.event._

/**
 * Created by xdev 18.08.11 at 0:52
 */

class GameViewScreen(controller: GameController) extends Canvas {

  addMouseListener(MouseProcessor)
  addMouseMotionListener(MouseProcessor)
  addKeyListener(KeyboardProcessor)

  private object Loop extends Runnable {
    private var lastLoopTime = 1L
    override def run() {
      while(true){
        val loopTime = lastLoopTime - System.currentTimeMillis
        if(controller.renderView().isDefined) {
           val view: View = controller.renderView().get
           view.update(loopTime / 1000)

           val strategy: BufferStrategy = getBufferStrategy()
           val graphics : Graphics2D = strategy.getDrawGraphics.asInstanceOf[Graphics2D]
           view.render(graphics)

           strategy.show()
        }
        lastLoopTime = System.currentTimeMillis
        Thread.sleep(10)
      }
    }
  }

  def run() {
    createBufferStrategy(2)
    new Thread(Loop).start()
  }


  object MouseProcessor extends MouseAdapter with MouseMotionListener {
    override def mouseClicked(e: MouseEvent) {
      if(controller.renderView().isDefined){
        controller.renderView().get.mouseClicked(e)
      }
    }

    override def mouseMoved(e: MouseEvent) {
      if(controller.renderView().isDefined){
        controller.renderView().get.mouseMoved(e)
      }
    }
  }

  object KeyboardProcessor extends KeyAdapter {
    override def keyPressed(e: KeyEvent){
      if(controller.renderView().isDefined){
        controller.renderView().get.keyPressed(e)
      }
    }
  }

}