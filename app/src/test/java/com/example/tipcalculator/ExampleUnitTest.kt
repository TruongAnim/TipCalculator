package com.example.tipcalculator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_calculate_tip_20(){
        val expect = 20f
        val actual = calculateTip(100f, 20f, isRound = true)
        assertEquals(expect, actual)
    }

    @Test
    fun test_calculate_tip_15(){
        val expect = 15.75f
        val actual = calculateTip(105f, 15f, isRound = false)
        assertEquals(expect, actual)
    }

    @Test
    fun test_calculate_tip_15_round(){
        val expect = 16f
        val actual = calculateTip(105f, 15f, isRound = true)
        assertEquals(expect, actual)
    }
}