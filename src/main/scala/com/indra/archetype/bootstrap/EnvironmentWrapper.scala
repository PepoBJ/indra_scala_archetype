package com.indra.archetype.bootstrap

trait EnvironmentWrapper {
  protected lazy val environment: String = EnvironmentFactory.getEnvironment
  protected lazy val developEnvironment: String = EnvironmentFactory.developEnvironment
}
