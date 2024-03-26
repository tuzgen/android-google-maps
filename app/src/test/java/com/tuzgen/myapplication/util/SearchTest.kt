package com.tuzgen.myapplication.util

import org.junit.Assert.assertEquals
import org.junit.Test

class SearchTest {

    @Test
    fun search() {
        val search = Search()
        val numbers = intArrayOf(-1, 0, 3, 5, 9, 12)
        val target = 9
        val expected = 4
        val actual = search.search(numbers, target)
        assertEquals(expected, actual)
    }
}