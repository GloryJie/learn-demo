package top.gloryjie.learn.scala.oo

/**
 * @author jie
 * @since 2020/2/9
 */
class Student(name: String, val studentNo: String) extends Person(name) {

    println("Student constructor in")

    override def toString: String = {
        // weight在Person中private修饰, 子类不可访问
        val info = String.format("Student={studentNo=%s, name=%s, age=%s, gender=%s}", studentNo, name, age, gender)
        info
    }

    println("Student constructor out")
}
