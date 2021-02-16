package diff

import scala.util.control.Breaks._

case class Diff(oldString: String,
                newString: String,
                operations: List[Operation]) {

  /**
    * Covert list of operation in human readable form
    * @return String
    */
  def stringify: String = {
    operations.foldLeft("")((diffSoFar, op) => {
      op match {
        case Insert(el) => diffSoFar + s"+$el"
        case Remove(el) => diffSoFar + s"-$el"
        case Equal(el)  => diffSoFar + el
      }
    })
  }

}

object Diff {

  /**
    * Generate the diff between two strings and generate list of operations needed to convert one string to another
    * @param first string
    * @param second string
    * @return Diff object containing both the strings and list of operations needed to convert one string to another
    */
  def generate(first: String, second: String) = {
    val lcs = LCS.create(first, second)
    var operations = List.empty[Operation]

    var l1 = first.length
    var l2 = second.length

    while (l1 != 0 || l2 != 0) {
      if (l1 == 0 && l2 == 0) break
      if (l2 == 0) {
        operations = operations :+ Remove(first.charAt(l1 - 1))
        l1 = l1 - 1
      } else if (l1 == 0) {
        operations = operations :+ Insert(second.charAt(l2 - 1))
        l2 = l2 - 1
      } else if (second.charAt(l2 - 1) == first.charAt(l1 - 1)) {
        operations = operations :+ Equal(second.charAt(l2 - 1))
        l1 = l1 - 1
        l2 = l2 - 1;
      } else if (lcs(l2)(l1) == lcs(l2 - 1)(l1)) {
        operations = operations :+ Insert(second.charAt(l2 - 1))
        l2 = l2 - 1
      } else {
        operations = operations :+ Remove(first.charAt(l1 - 1))
        l1 = l1 - 1
      }
    }

    Diff(first, second, operations.reverse)
  }
}
