package top.gloryjie.learn.scala.oo

/**
 * @author jie
 * @since 2020/2/8
 */
object OOApp {

    def main(args: Array[String]): Unit = {

        //        val person = new Person("jie",22)
        //        println(person)
        //
        //        val student = new Student("jojo", "123")
        //        println(student.toString)

        val math = new MathSubject()

        math.learn()

    }

}
