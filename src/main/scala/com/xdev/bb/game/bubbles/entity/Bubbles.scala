package com.xdev.bb.game.bubbles.entity

/**
 * Created by xdev 26.08.11 at 1:48
 */

object Bubbles {
  var rows: Int = 0
  var columns: Int = 0
  private var bubbles = Array.ofDim[Bubble](1, 1)

  def init(rows: Int, columns: Int){
    this.rows = rows
    this.columns = columns
    bubbles = Array.ofDim[Bubble](rows, columns)
  }

  def foreach(f: (Bubble) => Unit) {
    bubbles.foreach(_.foreach(f))
  }

  def filter(f: (Bubble) => Boolean): List[Bubble] = {
    var list = List[Bubble]()
    bubbles.foreach(filtered => {
      list = list ::: filtered.filter(f).toList
    })
    list
  }
  def clear(){ init(rows, columns)}

  def getBubble(row: Int, column: Int): Option[Bubble] = {
    if(row < 0 || row >= rows) return None
    if(column < 0 || column >= columns) return None
    return Some(bubbles(row)(column))
  }

  def getBubblesSelectionPath(head: Bubble): List[Bubble] = {
    foreach(b => b.marked = false) // unmark all bubbles
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

  def set(r: Int, c: Int, b: Bubble) = bubbles(r)(c) = b

}
