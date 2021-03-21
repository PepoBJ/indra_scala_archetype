package com.indra.archetype.bootstrap

object EnvironmentFactory {
  var environment: String = _

  def getEnvironment: String = this.environment
  def developEnvironment: String = "dev"

  def setEnvironment(environment: String): Unit = {
    this.environment = environment
  }
}
