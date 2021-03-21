package com.indra.archetype.transformation

import com.indra.archetype.bootstrap.SparkSessionWrapper
import com.indra.archetype.config.Configuration

trait Transformer[P, A] extends SparkSessionWrapper {
  def transform(config: Configuration[P, A]): Unit
}
