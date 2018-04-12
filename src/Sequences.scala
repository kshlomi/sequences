/**
  * @author skiraly 
  * @since 4/12/18.
  */
object Sequences {
  def main(args: Array[String]) = {
    val input = "1111111100000000"
    val output = new SequenceCalculator(input).compute()
    println(output)

    val input2 = "1111000011110000"
    val output2 = new SequenceCalculator(input2).compute()
    println(output2)

    val input3 = "1010101010101010"
    val output3 = new SequenceCalculator(input3).compute()
    println(output3)
  }
}
