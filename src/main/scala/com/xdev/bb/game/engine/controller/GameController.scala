package com.xdev.bb.game.engine.controller

import com.xdev.bb.game.engine.view.View

/**
 * Created by xdev 18.08.11 at 1:03
 */

trait GameController {
  def renderView(): Option[View]
}