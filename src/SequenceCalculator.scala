/**
  * @author skiraly
  * @since 4/12/18.
  */
class SequenceCalculator(input: String) {
  def compute() = {
    computeWithPattern(input, "", Seq())
  }

  private def computeWithPattern(input: String, pattern: String, patternSeq: Seq[(String, Int)]): String = {
    input match {
        // Halt condition
      case "" => patternSeq.map(pair => s"${pair._2}x${pair._1}").mkString(";")

        // Pattern hit
      case hadMatch if hadMatch.startsWith(pattern) && !pattern.isEmpty => {
        val newInput = hadMatch.drop(pattern.length)
        val newSeq = {
          if (patternSeq.isEmpty || patternSeq.last._1 != pattern) {
            patternSeq ++ Seq((pattern, 1))
          } else {
            patternSeq.dropRight(1) ++ Seq((patternSeq.last._1, patternSeq.last._2 + 1))
          }
        }
        computeWithPattern(newInput, pattern, newSeq)
      }

        // Try all other possible patterns
      case noMatch => {
        val allCompressions = (1 to input.length).map(len => {
          val newInput = noMatch.drop(len)
          val newPattern = noMatch.take(len)
          val newPatternSeq = patternSeq ++ Seq((newPattern, 1))
          computeWithPattern(newInput, newPattern, newPatternSeq)
        })

        allCompressions.reduceLeft((x,y) => if (x.length < y.length) x else y)
      }
    }
  }
}
