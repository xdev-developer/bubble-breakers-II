package com.xdev.bb.game.engine.listener

import com.xdev.bb.game.engine.controller.GameController
import java.awt.image.BufferStrategy
import java.awt.event._
import java.awt.{Graphics2D, Canvas}
import com.xdev.bb.game.engine.utils.{Timer, FPSCounter}

/**
 * Created by xdev 18.08.11 at 0:52
 */

class GameLoop(controller: GameController) extends Canvas {

  private object Loop extends Runnable {

    val MAX_FPS = 60
    val SHOW_FPS = true

    override def run() {
      var lastLoopTime = System.currentTimeMillis
      while(true){
        val loopTime = lastLoopTime - System.currentTimeMillis
        Timer.sync(MAX_FPS)

        FPSCounter.update()
        //Render
        if(controller.gameListener().isDefined) {
          val strategy: BufferStrategy = getBufferStrategy
          val graphics : Graphics2D = strategy.getDrawGraphics.asInstanceOf[Graphics2D]

          val width: Int = getSize.getWidth.toInt
          val height: Int = getSize.getHeight.toInt

          val listener: GameListener = controller.gameListener().get
          if(!listener.isInitialized){
            listener.init(graphics, (width, height))
            listener.isInitialized = true
          }
          listener.update(loopTime)
          graphics.clearRect(0, 0, width, height)
          listener.render(graphics, (width, height))

          if(SHOW_FPS){
            graphics.drawString("FPS : " + FPSCounter.getFPS + " delta : " + loopTime, 5, (height - 5.0f).toFloat)
          }

          strategy.show()
        }
        lastLoopTime = System.currentTimeMillis
      }
    }
  }

  private def initControllerListeners() {
    val strategy: BufferStrategy = getBufferStrategy
    val graphics : Graphics2D = strategy.getDrawGraphics.asInstanceOf[Graphics2D]

    val width: Int = getSize.getWidth.toInt
    val height: Int = getSize.getHeight.toInt
    controller.listeners.foreach(_.init(graphics, (width, height)))
  }

  def run() {
    addMouseListener(MouseProcessor)
    addMouseMotionListener(MouseProcessor)
    addKeyListener(KeyboardProcessor)
    createBufferStrategy(2)
    initControllerListeners()
    new Thread(Loop).start()
  }


  object MouseProcessor extends MouseAdapter with MouseMotionListener {
    override def mouseClicked(e: MouseEvent) {
      if(controller.gameListener().isDefined){
        if(controller.gameListener().get.isInitialized)
          controller.gameListener().get.mouseClicked(e)
      }
    }

    override def mouseMoved(e: MouseEvent) {
      if(controller.gameListener().isDefined){
        if(controller.gameListener().get.isInitialized)
          controller.gameListener().get.mouseMoved(e)
      }
    }
  }

  object KeyboardProcessor extends KeyAdapter {
    override def keyPressed(e: KeyEvent){
      if(controller.gameListener().isDefined){
        if(controller.gameListener().get.isInitialized)
          controller.gameListener().get.keyPressed(e)
      }
    }
  }
}