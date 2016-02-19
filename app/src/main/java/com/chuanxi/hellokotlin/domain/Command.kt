package com.chuanxi.hellokotlin.domain

/**
 * Created by tangjunjie on 2016/2/19.
 */
public interface Command<T> {
    fun execute():T
}