package com.indra.archetype.config

import com.indra.archetype.bootstrap.EnvironmentWrapper
import com.indra.archetype.transformation.Writer
import org.apache.spark.sql.DataFrame

case class OutputGeneric(table: String, path: String) extends Writer  with EnvironmentWrapper {
  override def write(outputDf: DataFrame): Unit = {
    val isDevelop = this.environment.startsWith(this.developEnvironment)

    readDataframe(outputDf, isDevelop)
  }

  private def readDataframe(outputDf: DataFrame, isDevelop: Boolean): Unit =
    isDevelop match {
      case true => outputDf.write.parquet(this.path)
      case _ => outputDf.write.saveAsTable(this.table) //TODO
    }
}
