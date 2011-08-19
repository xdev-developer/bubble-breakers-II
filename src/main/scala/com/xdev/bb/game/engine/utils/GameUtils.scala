package com.xdev.bb.game.engine.utils

import java.awt.{Font, Graphics2D}

/**
 * Created by xdev 20.08.11 at 0:02
 */

object GameUtils {

  def getStringSize(text: String, font: Font, g: Graphics2D): (Int, Int) = {
    val frc = g.getFontRenderContext
    val sw = font.getStringBounds(text, frc).getWidth
    val lm = font.getLineMetrics(text, frc)
    val sh = lm.getAscent + lm.getDescent
    return (sw.toInt, sh.toInt)
  }

}