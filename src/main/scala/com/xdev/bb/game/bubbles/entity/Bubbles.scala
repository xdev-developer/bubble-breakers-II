package com.xdev.bb.game.bubbles.entity

/**
 * Created by xdev 26.08.11 at 1:48
 */

object Bubbles {
  import collection.mutable.ArrayBuffer

  var rows: Int = 0
  var columns: Int = 0
  private val bubbles = new ArrayBuffer[Bubble]()

  def foreach(f: (Bubble) => Unit) {
    bubbles.foreach(f)
  }

  def filter(f: (Bubble) => Boolean): ArrayBuffer[Bubble] = {
    bubbles.filter(f)
  }

  def +=(b: Bubble) : ArrayBuffer[Bubble] = {
    bubbles += b
    bubbles
  }
  def clear(){ bubbles.clear()}

  def getBubble(row: Int, column: Int): Option[Bubble] = {
    if(row < 0 || row >= rows || column < 0 || column >= columns) return None
    val index = (row * columns) + column
    Some(bubbles(index))
  }

  def getBubblesSelectionPath(head: Bubble): List[Bubble] = {
    bubbles.foreach(b => b.marked = false) // unmark all bubbles
    head.marked = true
    var selection = List[Bubble](head)
    selection = selection ::: findBubbles (head.row, head.column, head.bubbleType)
    selection
  }

  private def findBubbles(row: Int, column: Int, bType: Int): List[Bubble] = {
    def checkBubbles(cRow: Int, cColumn: Int): List[Bubble] = {
      var checked = List[Bubble]()
      val bottomOpt = getBubble(cRow, cColumn)
      if(bottomOpt.isDefined){
        val bubble = bottomOpt.get
        if(!bubble.marked && !bubble.died && bubble.bubbleType == bType) {
          bubble.marked = true
          checked = bubble :: checked ::: findBubbles (bubble.row, bubble.column, bubble.bubbleType)
        }
      }
      checked
    }
    var selection = List[Bubble]()
    selection = selection ::: checkBubbles (row + 1, column) // top
    selection = selection ::: checkBubbles (row - 1, column) // bottom
    selection = selection ::: checkBubbles (row, column + 1) // left
    selection = selection ::: checkBubbles (row, column - 1) // right
    return selection
  }

}
