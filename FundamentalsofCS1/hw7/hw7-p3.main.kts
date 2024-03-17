// lenny and jaden

// -----------------------------------------------------------------
// Homework 7, Problem 3
// -----------------------------------------------------------------

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// A useful list abstraction that we haven't yet covered is `zip`
// (via the Kotlin docs): returns a list of pairs built from the
// elements of this list and the other list with the same index;
// the returned list has length of the shortest list.

// In this problem you'll practice using the imperative form of the
// accumulator pattern to implement this useful abstraction!

// TODO 1/1: apply the imperative form of the accumulator pattern
//           to finish designing the function, myZip.
//
//           Notes:
//           - While you'll need a mutable list inside the
//             function, you should return an immutable list.
//           - A Pair is a built-in type in Kotlin; look it up in
//             the docs to understand what it does!
//           - You have been supplied tests that should pass once
//             the function has been completed.
//

fun <T> myZip(
    listOne: List<T>,
    listTwo: List<T>,
): List<Pair<T, T>> {
    val pairedList: MutableList<Pair<T, T>> = mutableListOf()
    var shorterList = listOne

    // Determine which list is shorter
    if (listOne.size > listTwo.size) {
        shorterList = listTwo
    }

    return when {
        // If the list is empty, return such
        shorterList.isEmpty() -> pairedList.toList()
        // Otherwise, iterate through the lists
        else -> {
            // Interate through the lists and make pairs, add each pair to the pairedList Mutable List
            for (i in 0..shorterList.size - 1) {
                var currentPair = Pair(listOne[i], listTwo[i])
                // Add the new Pair
                pairedList.add(currentPair)
            }

            pairedList.toList()
        }
    }
}

@EnabledTest
fun testMyZip() {
    fun <A, B> helpTest(
        listA: List<A>,
        listB: List<B>,
        desc: String,
    ) {
        testSame(
            myZip(listA, listB),
            listA.zip(listB),
            "$desc: a zip b",
        )

        testSame(
            myZip(listB, listA),
            listB.zip(listA),
            "$desc: b zip a",
        )
    }

    helpTest(
        emptyList<String>(),
        emptyList<Int>(),
        "empty/empty",
    )

    helpTest(
        emptyList<String>(),
        listOf(1, 2),
        "empty/non-empty",
    )

    helpTest(
        listOf(1, 2, 3),
        listOf("a", "b"),
        "mixed",
    )
}

runEnabledTests(this)
