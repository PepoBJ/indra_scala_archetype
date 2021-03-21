package com.indra.archetype.transformation

import com.indra.archetype.bootstrap.SparkSessionWrapper
import org.apache.spark.sql.DataFrame

trait Writer extends SparkSessionWrapper {
  def write(outputDf: DataFrame): Unit
}
