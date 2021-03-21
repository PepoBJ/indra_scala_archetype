package com.indra.example

import com.indra.archetype.InitJob
import com.indra.archetype.config.Configuration
import com.indra.example.config.{AppParameter, ProcessParameter}
import com.indra.example.transformation.PrincipalTransformation
import org.apache.spark.sql.SparkSession

object AppJob extends InitJob[ProcessParameter, AppParameter] {
  override def runProcess(spark: SparkSession, config: Configuration[ProcessParameter, AppParameter]): Unit = {
    PrincipalTransformation.transform(config)
  }
}
