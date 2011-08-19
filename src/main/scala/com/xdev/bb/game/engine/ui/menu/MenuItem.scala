package com.xdev.bb.game.engine.ui.menu

import java.awt.geom.Rectangle2D
import java.awt.{Color, RenderingHints, Graphics2D, Font}
import java.awt.event.MouseEvent

/**
 * Created by xdev 19.08.11 at 2:18
 */

  class MenuItem(val text: String, action: () => Unit){

    private[menu] var font: Font = new Font("Helvetica", Font.PLAIN, 16)

    private var selected: Boolean = false
    private[menu] var textRectangle: Rectangle2D = null

    private[menu] var y: Int = 0
    private var textX: Int = 0
    private var textY: Int = 0
    private var isInitialized: Boolean = false

    private val scaleFactor: Double = 1.5

    private[menu] def render(g: Graphics2D, containerSize: (Int, Int)) {
      if(!isInitialized){
        init(g, containerSize._1, containerSize._2)
        isInitialized = true
      }

      val defaultFont = g.getFont
      val defaultColor = g.getColor

      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
      g.setFont(font)

      g.setColor(if(selected) {Color.BLACK} else Color.GRAY)
      g.drawString(text, textX, textY)

      g.setFont(defaultFont)
      g.setColor(defaultColor)
    }

    private[menu] def processMouseMove(e: MouseEvent) {
      if(textRectangle == null) return
      if(textRectangle.intersects(e.getPoint.x, e.getPoint.y, 1, 1)){
        selected = true
      }else {
        selected = false
      }
    }

    private[menu] def processMouseClick(e: MouseEvent) {
      if(e.getButton == 1 && selected){
        action()
      }
    }

    private def getTextRectangle(text: String, g: Graphics2D, scaleFactor: Double): Rectangle2D = {
      val metrics = g.getFontMetrics
      val rectangle : Rectangle2D = metrics.getStringBounds(text, g)
      rectangle.setRect(0, 0, rectangle.getWidth * scaleFactor, rectangle.getHeight * scaleFactor)
      rectangle
    }

    private def moveToCenterByX(r: Rectangle2D, containerWidth: Int, containerHeight: Int, yPos: Int):Rectangle2D = {
      r.setRect((containerWidth / 2) - (r.getWidth / 2), yPos, r.getWidth, r.getHeight)
      r
    }

    private def init(g: Graphics2D, containerWidth: Int , containerHeight: Int) {
      val frc = g.getFontRenderContext
      val sw = font.getStringBounds(text, frc).getWidth
      val lm = font.getLineMetrics(text, frc)
      val sh = lm.getAscent + lm.getDescent

      //Sorry for this shit
      textRectangle = moveToCenterByX(getTextRectangle(text, g, scaleFactor), containerWidth, containerHeight, y)
      textX = (textRectangle.getX + (textRectangle.getWidth - sw)/2).toInt
      textY = (textRectangle.getY + (textRectangle.getHeight + sh)/2 - lm.getDescent).toInt
    }
  }