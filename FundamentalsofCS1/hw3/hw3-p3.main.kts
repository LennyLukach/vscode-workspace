// -----------------------------------------------------------------
// Homework 3, Problem 3
// -----------------------------------------------------------------

import khoury.CapturedResult
import khoury.EnabledTest
import khoury.captureResults
import khoury.reactConsole
import khoury.testSame



// TODO 1/1: Design the program showNumbers that uses reactConsole
//           to simply print to the screen all the numbers in a
//           supplied list. For example, running...
//
//           showNumbers(listOf(5, 4, 3, 2, 1))
//
//           should show at the terminal...
//
//
//           5
//
//           4
//
//           3
//
//           2
//
//           1
//
//           Done!
//
//
//           (Note: this assumes the user doesn't type anything
//           between the numbers; even if they did, you should
//           ignore it in the transition function.)
//
//           Remember to start by thinking about your state
//           representation. While there are multiple approaches,
//           here are a couple suggestions:
//
//           1. Represent state as a list; transitioning to the
//              next list then involves list.drop(1) to produce
//              a new list without the first element of the old
//              list; you'll want to check for list.isEmpty()
//
//           2. Design a data class that keeps the supplied list
//              AND the current index (that you can increment
//              until it is no longer valid)
//
//           To help, you have a couple tests, which don't at all
//           depend on how *you* choose to represent state :)
//
//           If you need a refresher on reactConsole, recall that
//           there are a series of videos/files on Canvas walking
//           you through the design process and some example
//           programs :)
//

// fun <S> reactConsole(
//     initialState: S,
//     stateToText: (S) -> String,
//     nextState: (S, String) -> S,
//     isTerminalState: (S) -> Boolean,
//     terminalStateToText: (S) -> String = stateToText
// ):

fun showNumbers(numList: List<Int>) {
    reactConsole(
        numList,
        { numList -> numList.first().toString() },
        { numList, input -> numList.drop(1) },
        { numList -> numList.isEmpty() },
        { numList -> "Done!" },
    )
}

@EnabledTest
fun testShowNumbers() {
    // makes a captureResults-friendly function :)
    fun helpTest(numList: List<Int>): () -> Unit {
        fun showMyNumbers() {
            showNumbers(numList)
        }

        return ::showMyNumbers
    }

    testSame(
        captureResults(
            helpTest(emptyList<Int>()),
            "",
        ),
        CapturedResult(
            Unit,
            "Done!",
        ),
        "empty",
    )

    testSame(
        captureResults(
            helpTest(listOf(5, 4, 3, 2, 1)),
            "",
            "",
            "",
            "",
            "",
            "",
        ),
        CapturedResult(
            Unit,
            "5",
            "4",
            "3",
            "2",
            "1",
            "Done!",
        ),
        "5/4/3/2/1",
    )
}

// showNumbers(listOf(5, 4, 3, 2, 1))

testShowNumbers()
