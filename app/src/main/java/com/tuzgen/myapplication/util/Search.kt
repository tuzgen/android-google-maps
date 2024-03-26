package com.tuzgen.myapplication.util

class Search {
    fun search(numbers: IntArray, target: Int): Int {
        var left = 0
        var right = numbers.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                numbers[mid] == target -> return mid
                numbers[mid] < target -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return -1
    }
}