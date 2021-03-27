package diff

trait Operation {
  val symbol: String
  val element: Char
}

case class Insert(element: Char) extends Operation {
  override val symbol: String = "+"
}
case class Remove(element: Char) extends Operation {
  override val symbol: String = "-"
}
case class Equal(element: Char) extends Operation {
  override val symbol: String = ""
}
