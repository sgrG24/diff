package diff

import org.scalatest.{FlatSpec, Matchers}

class DiffTest extends FlatSpec with Matchers {

  "generate" should "return Diff with List of operations needed to convert first string to second string" in {
    val firstString = "ABC"
    val secondString = "AECD"

    Diff.generate(firstString, secondString) should be(
      Diff(
        "ABC",
        "AECD",
        List(Equal('A'), Remove('B'), Insert('E'), Equal('C'), Insert('D'))
      )
    )
  }

  "stringify" should "convert diff into human readable form" in {
    val diff = Diff.generate("ABC", "AECD")

    diff.stringify should be("A-B+EC+D")
  }

  "stringify2" should "concat adjacent operations into single operation" in {
    val diff = Diff.generate("ABC", "AECD")
    val diff2 = Diff.generate("ABCE", "ABCDF")

    diff.stringify2 should be("[A]-[B]+[E][C]+[D]")
    diff2.stringify2 should be("[ABC]-[E]+[DF]")
  }
}
