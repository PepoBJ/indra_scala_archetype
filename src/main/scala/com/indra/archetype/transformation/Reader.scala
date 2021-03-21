package com.indra.archetype.transformation

import com.indra.archetype.bootstrap.SparkSessionWrapper
import org.apache.spark.sql.DataFrame

trait Reader extends SparkSessionWrapper {
  def read(): DataFrame
}
