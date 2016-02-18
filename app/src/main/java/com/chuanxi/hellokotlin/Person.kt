package com.chuanxi.hellokotlin

import android.util.Log

/**
 * Created by tangjunjie on 2016/2/18.
 */
class Person (name:String,age:Int) :Animal(name){

    var age : Int = 0
    init {
        super.name = name
        this.age = age
    }


    fun sayHello() {
        age = add(4)
        Log.e("Person","Hello,I'm $name my age is $age");
    }

    fun add(x:Int) : Int = age+x
}