package com.xdev.bb.game.engine.utils

/**
 * Created by xdev 19.08.11 at 0:03
 */

object Timer {

    var timeThen: Long = System.nanoTime()

    def sync(fps: Int) {
        Thread.`yield`()
        val gapTo = 1000000000L / fps + timeThen;
        var timeNow = System.nanoTime();
         try {
           while (gapTo > timeNow) {
             Thread.sleep((gapTo-timeNow) / 2000000L)
             timeNow = System.nanoTime()
           }
          } catch {case _ =>}
      timeThen = timeNow
    }
}