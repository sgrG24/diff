package diff

trait Operation {
  val element: Char
}

case class Insert(element: Char) extends Operation
case class Remove(element: Char) extends Operation
case class Equal(element: Char) extends Operation
