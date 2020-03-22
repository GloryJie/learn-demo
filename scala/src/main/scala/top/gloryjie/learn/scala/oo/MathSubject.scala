package top.gloryjie.learn.scala.oo

/**
 * @author jie
 * @since 2020/2/10
 */
class MathSubject extends AbstractLearn {

    override var subject: String = _


    override def learn(): Unit = {
        println("learn " + subject)
    }

}
