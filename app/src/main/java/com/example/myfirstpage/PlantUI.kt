package com.example.myfirstpage

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantUI(val id: Int, val title: String) : Parcelable


















//class Test<out T : Number> {
//    private var test: T? = null
//
//    fun get(): T {
//        val t = test
//        return t ?: error("123")
//    }
//
//    fun set(t: T) {
//        test = t
//    }
//
//}
//
//fun <C : Number> createTest(c: C): C {
//    val t: Test<C> = Test()
//    t.set(c)
//    return t.get()
//}
//
//fun main() {
//    val z: Number = createTest<Float>(5f)
//    val l1 = mutableListOf<Int>()
//    val l2 = mutableListOf<Number>()
//    val l3 = mutableListOf<Any>()
//    var r: MutableList<out Number> = l1
//    r.add(5)
//    val t:Number = r.get(1)
//}
