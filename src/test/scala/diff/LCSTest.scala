package diff

import org.scalatest.{FlatSpec, Matchers}

class LCSTest extends FlatSpec with Matchers {

  "create" should "return the 2D matrix for computing LCS using dynamic programming" in {
    val s1 = "ABC"
    val s2 = "AECD"

    val expected = Array(
      Array(0, 0, 0, 0),
      Array(0, 1, 1, 1),
      Array(0, 1, 1, 1),
      Array(0, 1, 1, 2),
      Array(0, 1, 1, 2)
    )

    LCS.create(s1, s2) should be(expected)
  }
}
