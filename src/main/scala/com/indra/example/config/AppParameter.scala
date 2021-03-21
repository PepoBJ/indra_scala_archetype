package com.indra.example.config

import com.indra.archetype.config.{InputGeneric, OutputGeneric}
import java.util.{Map => JMap}

case class AppParameter(writeMode: String,
                        input: JMap[String, InputGeneric],
                        output: JMap[String, OutputGeneric])