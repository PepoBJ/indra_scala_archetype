package com.indra.archetype.config

import org.apache.spark.sql.Column
import org.apache.spark.sql.functions.col

case class FieldGeneric (fieldName: String,
                         typeName: String,
                         key: Boolean) {
  def getColumn: Column = {
    col(this.fieldName)
  }
}