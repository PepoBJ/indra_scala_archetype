package com.indra.archetype.common

import java.io.IOException
import java.nio.file.Paths
import scala.io.Source
import scala.util.parsing.json.JSON
import com.google.json.JsonSanitizer.sanitize

object JsonManager {
  def getMapValues(filePath: String): Map[String, String] = {
    try {
      val fileNormalizePath = Paths.get(filePath).normalize.toString
      val fileSource = Source.fromFile(fileNormalizePath)
      val dataSource = try fileSource.mkString finally fileSource.close()

      val dataJSON = try {
        JSON.parseFull(sanitize(dataSource))
      }
      catch {
        case e: IOException => new Exception("Error in parsing", e)
          null
      }

      dataJSON.get.asInstanceOf[Map[String, String]]
    }
    catch {
      case e: Exception => null
    }
  }

  def getString(filePath: String): String = {
    val fileNormalizePath = Paths.get(filePath).normalize.toString
    val fileSource = Source.fromFile(fileNormalizePath)
    try fileSource.mkString finally fileSource.close()
  }
}
