package top.gloryjie.learn.scala

/**
 * 如何定义一个Scala函数
 *
 * @author jie
 * @since 2020/2/7
 */
object FunctionDefinition {

    def main(args: Array[String]): Unit = {
        var result = add(2, 3)

        // 在无入参的函数,可省略括号
        result = ten()
        result = ten

        println(result)
        printHelloWorld()

        sayYourName()
        sayYourName("JOJO")

        // 可以打乱入参的顺序, 但是需要给定入参名称
        println(speed(100, 10))
        println(speed(distance = 100, time = 10))
        println(speed(time = 10, distance = 100))

        // 可变参数
        println(sum())
        println(sum(1))
        println(sum(1, 2))
        println(sum(1, 2, 3))
    }


    /**
     * def 为定义函数关键字
     * add 函数名称
     * a:Int, a为入参名, 冒号后卫入参类型
     * :Int, 标识返回值类型
     *
     * @param a
     * @param b
     * @return
     */
    def add(a: Int, b: Int): Int = {
        // 最后一行为为return语句
        a + b
    }


    /**
     * 最简函数,无入参, 函数体只有一行
     * 在函数体只有一行的时候,可以省略 {}
     *
     * @return
     */
    def ten() = 10


    /**
     * 无返回值函数,使用Unit来表示
     */
    def printHelloWorld(): Unit = {
        println("hello world")
    }


    /**
     * 函数入参可以具有默认值, 即可不填
     *
     * @param name
     */
    def sayYourName(name: String = "DefaultName"): Unit = {
        println("your name is: " + name)
    }


    /**
     * 命名参数传递, 既可以不按照函数定义入参的顺序传递,但是需要给定参数名
     *
     * @param distance
     * @param time
     * @return
     */
    def speed(distance: Double, time: Double): Double = {
        distance / time
    }

    /**
     * 可变参数函数
     *
     * @param nums
     * @return
     */
    def sum(nums: Int*): Int = {
        var count = 0
        for (i <- nums) {
            count += i
        }
        count
    }

}
