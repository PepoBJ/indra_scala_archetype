package com.indra.archetype

import com.indra.archetype.bootstrap.{Environment, SparkSessionWrapper}
import com.indra.archetype.config.Configuration
import org.apache.spark.sql.SparkSession

import scala.reflect.ClassTag

abstract class InitJob[P, A](implicit PCT: ClassTag[P], ACT: ClassTag[A]) extends Environment[P, A]
  with SparkSessionWrapper {
  def main(args: Array[String]): Unit = {
    this.initialize(args)

    runProcess(this.spark, this.getConfiguration())
  }

  def runProcess(spark: SparkSession, config: Configuration[P, A]): Unit = {
    throw new NotImplementedError("Not implement main process")
  }
}
