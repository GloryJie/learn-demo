package top.gloryjie.learn.scala.oo

/**
 * @author jie
 * @since 2020/2/8
 */
class Person(val name: String) {

    println("Person constructor in")

    // 默认访问修饰符为public
    var gender: String = _
    protected var age: Int = _
    private var weight: Int = _

    println("Person constructor out")

    def this(name: String, age: Int) {
        this(name)
        this.age = age
    }

    def this(name: String, age: Int, gender: String) {
        this(name, age)
        this.gender = gender
    }

    def eat(): Unit = {
        println(name + " eat...")
    }

    override def toString: String = {
        val info = String.format("person={name=%s, age=%s, gender=%s, wieght=%s}", name, age, gender, weight)
        info
    }
}
