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
        computeWithPattern(hadMatch.drop(pattern.length),
          pattern,
          patternSeq.updated(patternSeq.length - 1, (patternSeq.last._1, patternSeq.last._2 + 1)))
      }

      // Try all other possible patterns
      case noMatch => {
        val allCompressions = (1 to input.length).map(len => {
          computeWithPattern(noMatch.drop(len),
            noMatch.take(len),
            patternSeq ++ Seq((noMatch.take(len), 1)))
        })

        allCompressions.reduceLeft((x, y) => if (x.length < y.length) x else y)
      }
    }
  }
}
