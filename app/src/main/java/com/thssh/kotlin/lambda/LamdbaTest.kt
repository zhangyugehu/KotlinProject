package com.thssh.kotlin.lambda

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/07/18
 */
fun testNull(str:String){
    println("test null ---")
}

fun testLambda(str:String, method1 : (String)->(Unit)){
    method1("$str test Lambda ---")
}

fun main(args: Array<String>) {
//    testLambda (::testNull)

//    testLambda ("fsaf", { str ->
//        run {
//            println("--- start")
//            println("lalala $str")
//            println("--- end")
//        }
//    })

    // 如果最后一个参数是lambda
    testLambda("hahaha"){str ->
        run {
            println("--- start")
            println("lalala $str")
            println("--- end")
        }
    }
}