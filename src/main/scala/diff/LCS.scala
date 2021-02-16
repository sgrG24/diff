package diff

object LCS {

  def max(i: Int, i1: Int) = if (i > i1) i else i1

  def print2DArray(array: Array[Array[Int]], rows: Int, columns: Int) = {
    for (i <- 0 until rows) {
      for (j <- 0 until columns) {
        print(array(i)(j))
        print(" ")
      }
      println()
    }
  }

  def create(a: String, b: String) = {
    val aLength = a.length
    val bLength = b.length

    val matrix = Array.ofDim[Int](bLength + 1, aLength + 1)

    for (i <- 1 to bLength) {
      for (j <- 1 to aLength) {
        if (b.charAt(i - 1) equals a.charAt(j - 1))
          matrix(i)(j) = 1 + matrix(i - 1)(j - 1)
        else matrix(i)(j) = max(matrix(i - 1)(j), matrix(i)(j - 1))
      }
    }

    matrix
  }
}
