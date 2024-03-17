// lenny and jaden

import khoury.testSame

// -----------------------------------------------------------------
// Homework 6, Problem 1
// -----------------------------------------------------------------

// In this problem, you'll practice with looping and mutation to
// implement a form of the functional `map` function to actually
// change the elements of a supplied list (instead of returning
// a new list).

// TODO 1/1: Design the function myInplaceMap, which transforms the
//           elements of a supplied list using a supplied function.
//
//           For example, if the function was supplied [1, 2, 3] as
//           a mutable list, as well as a function that doubles a
//           supplied number, the supplied list would be [2, 4, 6]
//           after the function completed (note that the function
//           itself should not return any value).
//
//           Make sure to sufficiently test this function,
//           including empty vs lists that have elements, as well
//           as differing types.
//

var test1 = mutableListOf(1, 2, 3)
var result1 = mutableListOf(2, 4, 6)
var testEmptyList: MutableList<Int> = mutableListOf()
var resultEmptyList: MutableList<Int> = mutableListOf()
var test2 = mutableListOf<String>("a", "2", "c")
var result2 = mutableListOf<String>("aa", "22", "cc")

fun <I> myInplaceMap(
    myList: MutableList<I>,
    pred: (I) -> I,
) {
    for (i in 0..myList.size - 1) {
        myList[i] = pred(myList[i])
    }
}

// Test case 1

testSame(test1, mutableListOf(1, 2, 3), "Before Change")

myInplaceMap(test1, { it * 2 })

testSame(test1, result1, "After Change")

print("\n")

// Test case 2

testSame(test2, mutableListOf("a", "2", "c"), "Before Change")

myInplaceMap(test2, { it + it })

testSame(test2, result2, "After Change")

print("\n")

// Test case 3

myInplaceMap(testEmptyList, { it * 2 })

testSame(testEmptyList, resultEmptyList, "Empty List")
