package top.gloryjie.learn.scala

/**
 * 循环语法
 * to
 * range
 * until
 *
 * @author jie
 * @since 2020/2/7
 */
object LoopGrammar {

    def main(args: Array[String]): Unit = {
        //    rangeTest()
        //    toTest()
        //    untilTest()
        arrayLoopTest()
    }

    def rangeTest(): Unit = {
        /** ****** range ***********/

        // range, 左闭右开, 默认步长为1
        for (i <- Range(1, 10)) {
            print(i + " ")
        }
        println()

        // range, 指定步长, 步长不能为0, 可负可正
        for (i <- Range(1, 10, 2)) {
            print(i + " ")
        }
        println()
    }


    def toTest(): Unit = {
        /** ******* to ****************/
        // to底层基于range
        // to ,左右都是闭区间语法
        for (i <- 1 to 10) {
            print(i + " ")
        }
        println()

        for (i <- 1.to(10)) {
            print(i + " ")
        }
        println()
    }

    def untilTest(): Unit = {
        /** ******* until ****************/
        // until底层基于range

        // until, 左闭右开
        for (i <- 1 until 10) {
            print(i + " ")
        }
        println()

        for (i <- 1.until(10)) {
            print(i + " ")
        }
        println()
    }


    /**
     * 数组遍历
     */
    def arrayLoopTest(): Unit = {
        var courses = Array("java", "go", "scala")

        // 普通的foreach信息
        for (course <- courses) {
            print(course + " ")
        }
        println()

        // foreach函数来遍历
        courses.foreach(course => print(course + " "))
        println()
    }

}
