package com.xdev.bb.game.engine.manager

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import io.Source
import collection.mutable.{HashMap, ArrayBuffer}
import java.util.jar.JarFile
import java.net.URLDecoder

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
      val dir: String = if (dirPath.startsWith("/")) dirPath.substring(1) else dirPath
      val jarPath = uri.getPath().substring(5, uri.getPath().indexOf("!"))
      val jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"))
      val entries = jar.entries()
      while(entries.hasMoreElements()) {
        val entry = entries.nextElement()
        if(entry.getName.startsWith(dir) && !entry.isDirectory){
          val fileName = entry.getName.replace(dir, "")
          images += (fileName -> ImageIO.read(jar.getInputStream(entry)))
        }
      }
      return;
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