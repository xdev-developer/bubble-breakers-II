package com.xdev.bb.game.controller

import com.xdev.bb.game.view.View

/**
 * Created by xdev 18.08.11 at 1:03
 */

trait GameController {
  def renderView(): Option[View]
}