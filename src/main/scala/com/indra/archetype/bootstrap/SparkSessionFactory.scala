package com.indra.archetype.bootstrap

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkSessionFactory {
  var spark: SparkSession = _

  def getSparkSession: SparkSession = this.spark

  def setSession(sparkConfiguration: Map[String, String]): Unit = {
    val sparkConf = new SparkConf(true).setAll(sparkConfiguration)
    this.spark = SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()
  }

  def setSession(): Unit = {
    this.spark = SparkSession.builder().appName("MotorIngesta").master("local[*]").getOrCreate()
  }
}
