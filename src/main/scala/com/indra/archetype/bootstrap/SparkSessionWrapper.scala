package com.indra.archetype.bootstrap

import org.apache.spark.sql.SparkSession

trait SparkSessionWrapper {
  protected lazy val spark: SparkSession = SparkSessionFactory.getSparkSession
}
