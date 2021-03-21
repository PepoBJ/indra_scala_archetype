package com.indra.example.transformation

import com.indra.archetype.config.Configuration
import com.indra.archetype.transformation.Transformer
import com.indra.example.config.{AppParameter, ProcessParameter}

object PrincipalTransformation extends Transformer[ProcessParameter, AppParameter]{
  override def transform(config: Configuration[ProcessParameter, AppParameter]): Unit = {
    val coinDF = config.appConfig.input.get("m_desmoneda").read()
    val factoringDF = config.appConfig.input.get("m_cuentafactoring").read()

    val outputDf = factoringDF.join(coinDF, Seq("codmoneda"), "left")

    config.appConfig.output.get("m_indra").write(outputDf)

    print("Hello World")
  }
}
