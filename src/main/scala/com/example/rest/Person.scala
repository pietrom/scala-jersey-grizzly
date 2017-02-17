package com.example.rest

import scala.beans.BeanProperty
import javax.xml.bind.annotation._

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = Array("group"))
@XmlRootElement(name = "person")
class Person(@BeanProperty var firstName: String, @BeanProperty var lastName: String) {
}
