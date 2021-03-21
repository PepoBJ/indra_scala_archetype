package com.indra.archetype.bootstrap

import com.indra.archetype.common.{GsonManager, JsonManager}
import com.indra.archetype.config.{Configuration, JobParameter}
import com.typesafe.config.{Config, ConfigFactory, ConfigRenderOptions}
import org.apache.spark.sql.SparkSession

import scala.reflect.ClassTag

abstract class Environment[P, A] (implicit PCT: ClassTag[P], ACT: ClassTag[A]) {
  private val configFileName: String = "application.conf"
  private val configJobName: String = "job"
  private val configAppName: String = "app"
  private val developmentName: String = "dev"

  private var jobConfig: JobParameter = _
  private var appConfig: A = _
  private var processConfig: P = _

  private var resourcePath: String = _
  private var sequenceNumber: String = _

  def initialize(args: Array[String]): Unit = {
    resourcePath = args(1)
    sequenceNumber = args(2)

    this.loadConfiguration()
    this.loadSparkSession()
    this.loadProcessConfig()
  }

  def getConfiguration(): Configuration[P, A] = {
    Configuration(this.jobConfig, this.processConfig, this.appConfig)
  }

  private def loadConfiguration(): Unit = {
    val config = ConfigFactory.load(configFileName)
    val jobParameters: String = getConfigRoot(config, configJobName)
    val appParameters: String = getConfigRoot(config, configAppName)

    this.jobConfig = GsonManager.convertToObject[JobParameter](jobParameters)
    this.appConfig = GsonManager.convertToObject[A](appParameters)
  }

  private def loadSparkSession(): Unit = {
    EnvironmentFactory.setEnvironment(this.jobConfig.environment.toLowerCase)

    if(this.jobConfig.environment.toLowerCase.startsWith(this.developmentName)) {
      SparkSessionFactory.setSession()
    }
    else {
      val sparkConfigPath = s"${resourcePath}${this.jobConfig.sparkConfigName}_${this.jobConfig.appName}_${this.sequenceNumber}.json"
      val sparkConfiguration = JsonManager.getMapValues(sparkConfigPath)
      SparkSessionFactory.setSession(sparkConfiguration)
    }
  }

  private def loadProcessConfig(): Unit = {
    val processConfigPath = s"${resourcePath}${this.jobConfig.processConfigName}_${this.jobConfig.appName}_${this.sequenceNumber}.json"
    val processConfiguration = JsonManager.getString(processConfigPath)
    this.processConfig = GsonManager.convertToObject[P](processConfiguration)
  }

  private def getConfigRoot(config: Config, configName: String): String = {
    config.getConfig(configName).root().render(ConfigRenderOptions.concise())
  }
}
