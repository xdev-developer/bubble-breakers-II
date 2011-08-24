package com.xdev.bb.game.engine.utils

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.awt.{RenderingHints, Font, Graphics2D}

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

  def loadImage(path: String): BufferedImage = {
    ImageIO.read(getClass.getResourceAsStream(path))
  }

  def hiQuality(g: Graphics2D, render : () => Unit){
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
    render()
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF)
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED)
  }
}