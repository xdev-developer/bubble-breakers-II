package com.xdev.bb.game.engine.manager

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import io.Source
import collection.mutable.{HashMap, ArrayBuffer}

/**
 * Created by xdev 20.08.11 at 1:27
 */

object ResourceManager {

  val images = new HashMap[String, BufferedImage]()

  def loadImages(dirPath : String) {
    val uri = getClass.getResource(dirPath)

    if(uri != null && uri.getProtocol == "file"){
      loadImages(new File(uri.toURI))
      return;
    }

    if(uri != null && uri.getProtocol == "jar"){

    }
  }

  def loadImages(dir: File){
     if(!dir.isDirectory) return;
     val listFiles = dir.listFiles
     for(file <- listFiles){
      images += (file.getName -> ImageIO.read(file))
     }
  }

}