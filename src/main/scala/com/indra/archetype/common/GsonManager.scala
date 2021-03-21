package com.indra.archetype.common

import scala.reflect._
import com.google.gson.Gson

object GsonManager {
  def convertToObject[T: ClassTag](jsonData: String): T = {
    val gson = new Gson()
    gson.fromJson(jsonData, classTag[T].runtimeClass)
  }
}
