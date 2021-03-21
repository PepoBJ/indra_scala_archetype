package com.indra.archetype.config

import com.indra.archetype.bootstrap.{EnvironmentWrapper, SparkSessionWrapper}
import com.indra.archetype.transformation.Reader
import org.apache.spark.sql.{DataFrame, Dataset}

import java.util.{List => JList}
import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

case class InputGeneric(table: String,
                        path: String,
                        fields: JList[FieldGeneric]) extends Reader with EnvironmentWrapper {

  override def read(): DataFrame = {
    val columns = this.fields.map(f => f.getColumn).toSeq

    val isDevelop = this.environment.startsWith(this.developEnvironment)
    val dataFrame = readDataframe(isDevelop)

    dataFrame.select(columns:_*)
  }

  private def readDataframe(isDevelop: Boolean): DataFrame =
    isDevelop match {
      case true => this.spark.read.parquet(this.path)
      case _ => this.spark.table(this.table)
    }
}