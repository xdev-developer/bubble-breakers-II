package com.xdev.bb.game.engine.view

import com.xdev.bb.game.engine.controller.GameController
import java.awt.image.BufferStrategy
import java.awt.event._
import java.awt.{RenderingHints, Graphics2D, Color, Canvas}
import com.xdev.bb.game.engine.utils.{Timer, FPSCounter}

/**
 * Created by xdev 18.08.11 at 0:52
 */

class GameViewScreen(controller: GameController) extends Canvas {

  addMouseListener(MouseProcessor)
  addMouseMotionListener(MouseProcessor)
  addKeyListener(KeyboardProcessor)

  private object Loop extends Runnable {

    override def run() {
      var lastLoopTime = System.currentTimeMillis
      while(true){
        val loopTime = lastLoopTime - System.currentTimeMillis
        Timer.sync(60)

        FPSCounter.update()
        if(controller.renderView().isDefined) {
           val view: View = controller.renderView().get
           view.update(loopTime)

           val strategy: BufferStrategy = getBufferStrategy
           val graphics : Graphics2D = strategy.getDrawGraphics.asInstanceOf[Graphics2D]

           val size = getSize
           view.render(graphics, size.getWidth, size.getHeight)
           graphics.drawString("FPS : " + FPSCounter.getFPS + " delta : " + loopTime, 5, (size.getHeight - 5.0f).toFloat)

           strategy.show()
        }
        lastLoopTime = System.currentTimeMillis
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