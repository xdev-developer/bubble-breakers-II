package com.xdev.bb.game.engine.utils

/**
 * Created by xdev 18.08.11 at 23:48
 */

object FPSCounter {
  private var lastFpsTime = 1L
  private var fpsCount = 1L
  private var fps = 1L

  def update() {
      //Calc fps
      val currentTime = System.currentTimeMillis
      if(currentTime - lastFpsTime > 1000){
        fpsCount = fps
        lastFpsTime = currentTime
        fps = 0
      }
      fps += 1
  }

  def getFPS = fpsCount
}
