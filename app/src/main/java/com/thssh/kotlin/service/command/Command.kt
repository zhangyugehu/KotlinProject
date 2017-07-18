package com.thssh.kotlin.service.command

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/07/17
 */
interface Command<T> {
    fun execute() : T
}