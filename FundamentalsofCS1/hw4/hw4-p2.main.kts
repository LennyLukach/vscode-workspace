// -----------------------------------------------------------------
// Homework 4, Problem 2
// -----------------------------------------------------------------

import khoury.EnabledTest
import khoury.testSame

// In this problem we'll practice a more functional approach:
// recursively iterating through self-referential lists...

// a self-referential list of numbers is either...
sealed class SRListNums {
    // no numbers
    object End : SRListNums()

    // an element, as well as
    // a reference to the rest
    // of the list (which could
    // be either...)
    data class Element(val num: Int, val rest: SRListNums) : SRListNums()
}

// example of building up [8, 6, 7, 5, 3, 0, 9]
val srEmpty = SRListNums.End
val sr9 = SRListNums.Element(9, srEmpty)
val sr09 = SRListNums.Element(0, sr9)
val sr309 = SRListNums.Element(3, sr09)
val sr5309 = SRListNums.Element(5, sr309)
val sr75309 = SRListNums.Element(7, sr5309)
val sr675309 = SRListNums.Element(6, sr75309)
val sr8675309 = SRListNums.Element(8, sr675309)

// TODO 1/3: Finish designing the function sRListToList, which
//           converts a self-referential list of integers to an
//           equivalent Kotlin list. You have tests to help, and
//           may type-driven development guide you to a well-
//           designed solution :)
//

fun sRListToList(srList: SRListNums): List<Int> {
    return when (srList) {
        SRListNums.End -> emptyList()
        is SRListNums.Element -> listOf(srList.num) + sRListToList(srList.rest)
    }
}

@EnabledTest
fun testSRListToList() {
    testSame(
        sRListToList(srEmpty),
        emptyList<Int>(),
        "empty",
    )

    testSame(
        sRListToList(sr9),
        listOf(9),
        "[9]",
    )

    testSame(
        sRListToList(sr09),
        listOf(0, 9),
        "[0, 9]",
    )

    testSame(
        sRListToList(sr8675309),
        listOf(8, 6, 7, 5, 3, 0, 9),
        "jenny",
    )
}

// TODO 2/3: Now try the opposite - listToSRList.
//
//           Hint: think about one element of the Kotlin list
//                 at a time, from left-to-right; if you had
//                 nothing in the list, what would that be?
//                 otherwise, how could you then make a self-
//                 refential list recursively?
//

fun listToSRList(list: List<Int>): SRListNums {
    return when (list) {
        emptyList<Int>() -> SRListNums.End
        else -> SRListNums.Element(list.first(), listToSRList(list.drop(1)))
    }
}

@EnabledTest
fun testListToSRList() {
    testSame(
        listToSRList(emptyList<Int>()),
        srEmpty,
        "empty",
    )

    testSame(
        listToSRList(listOf(9)),
        sr9,
        "[9]",
    )

    testSame(
        listToSRList(listOf(0, 9)),
        sr09,
        "[0, 9]",
    )

    testSame(
        listToSRList(listOf(8, 6, 7, 5, 3, 0, 9)),
        sr8675309,
        "jenny",
    )
}

// TODO 3/3: Lastly, finish designing filterSRList as a way to do
//           what filter does for Kotlin lists. Let type-driven
//           programming be your guide, and you have tests
//           for clarity!
//
//           Note: you should NOT use the conversion functions
//                 above just to use Kotlin's filter - try to do
//                 it recursively!
//

fun filterSRList(
    srList: SRListNums,
    checkEven: (Int) -> Boolean,
): SRListNums {
    return when (srList) {
        SRListNums.End -> SRListNums.End
        is SRListNums.Element -> {
            if (checkEven(srList.num)) {
                SRListNums.Element(srList.num, filterSRList(srList.rest, checkEven))
            } else {
                filterSRList(srList.rest, checkEven)
            }
        }
    }
}

@EnabledTest
fun testFilterSRList() {
    // is a supplied number even?
    fun isEven(num: Int): Boolean {
        return num % 2 == 0
    }

    // is a supplied number non-positive?
    fun isNonPositive(num: Int): Boolean {
        return num <= 0
    }

    testSame(
        filterSRList(srEmpty, ::isEven),
        srEmpty,
        "empty",
    )

    testSame(
        filterSRList(sr8675309, ::isEven),
        SRListNums.Element(
            8,
            SRListNums.Element(
                6,
                SRListNums.Element(
                    0,
                    SRListNums.End,
                ),
            ),
        ),
        "even",
    )

    testSame(
        filterSRList(sr8675309, ::isNonPositive),
        SRListNums.Element(
            0,
            SRListNums.End,
        ),
        "non-positive",
    )
}

testSRListToList()
testListToSRList()
testFilterSRList()
