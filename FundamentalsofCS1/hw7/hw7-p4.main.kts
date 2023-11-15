// lenny and jaden

// -----------------------------------------------------------------
// Homework 7, Problem 4
// -----------------------------------------------------------------

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// In this problem, you'll practice applying the functional
// version of the accumulator pattern to reimplement the useful
// `joinToString` function.

// TODO 1/2: Finish designing the myJoinToString function, which
//           creates a string from all the elements of the supplied
//           list separated using a supplied separator.
//
//           Notes:
//           - For purposes of this problem, you must apply the
//             recursive form of the accumulator pattern.
//           - Recall that we can recur on the structure of a
//             Kotlin list by checking if it's empty and if not,
//             combine the first element with the recursive result
//             of the remaining list elements.
//           - You have been supplied tests that should pass once
//             the function has been completed.
//

fun <T> myJoinToString(
    inputList: List<T>,
    seperator: String,
): String {
    when {
        inputList.isEmpty() -> return ""
        else -> {
            val newList = inputList.drop(1)

            if (inputList.size == 1) {
                return "${inputList[0]}" + myJoinToString(newList, seperator)
            } else {
                return "${inputList[0]}" + seperator + myJoinToString(newList, seperator)
            }
        }
    }
}

@EnabledTest
fun testMyJoinToString() {
    fun <T> helpTest(
        l: List<T>,
        sep: String,
        desc: String,
    ) {
        testSame(
            myJoinToString(l, sep),
            l.joinToString(sep),
            desc,
        )
    }

    helpTest(emptyList<Int>(), ", ", "empty")
    helpTest(listOf(1), ", ", "single")
    helpTest(listOf(1, 2, 3), ", ", "nums")
    helpTest(listOf("alice", "bob", "chris", "dan"), "-", "txts")
}

// TODO 2/2: Now given that your recursive implementation works,
//           finish designing the function myJoinToStringFold to
//           produce the same result, but effectively using the
//           fold abstraction (which iterates for itself).
//
//           You have been supplied tests that should pass once
//           the function has been completed; this time, instead
//           of comparing against the built-in Kotlin version, they
//           show that your two implementations solve the same
//           problem!
//

fun <T> myJoinToStringFold(
    inputList: List<T>,
    seperator: String,
): String {
    if (inputList.isEmpty()) {
        return ""
    } else {
        val firstItem = inputList[0]
        val newList = inputList.drop(1)

        return newList.fold("$firstItem", { acc, item -> "$acc$seperator$item" })
    }
}

@EnabledTest
fun testMyJoinToStringFold() {
    fun <T> helpTest(
        l: List<T>,
        sep: String,
        desc: String,
    ) {
        testSame(
            myJoinToString(l, sep),
            myJoinToStringFold(l, sep),
            desc,
        )
    }

    helpTest(emptyList<Int>(), ", ", "empty")
    helpTest(listOf(1), ", ", "single")
    helpTest(listOf(1, 2, 3), ", ", "nums")
    helpTest(listOf("alice", "bob", "chris", "dan"), "-", "txts")
}

runEnabledTests(this)
