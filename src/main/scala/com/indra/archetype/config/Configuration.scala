package com.indra.archetype.config

case class Configuration[ProcessConfigType, AppConfigType](jobConfig: JobParameter,
                                                           processConfig: ProcessConfigType,
                                                           appConfig: AppConfigType)
