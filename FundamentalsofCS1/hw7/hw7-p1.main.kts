// lenny and jaden

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// -----------------------------------------------------------------
// Homework 7, Problem 1
// -----------------------------------------------------------------

// In this problem, you'll practice applying list abstractions in
// order to design a useful algorithm, topK!

// When working with data, it is often helpful to be able to get
// the "best" set of data points, by some measure. For example...
//
// a) The single longest string in a list of strings
// b) The 2 smallest numbers in a list of integers
// c) The 5 points that are closest to the y-axis
//    (i.e., the absolute value of their x-coordinate
//    is smallest).
//
// To start, consider the following definition of an "evaluation"
// function: one that takes an input of some type and associates
// an output "score" (where bigger scores are understood to be
// better for the task at hand):

// a way to "score" a particular type of data
typealias EvaluationFunction<T> = (T) -> Int

fun stringToScore(element: String): Int  {
    // Return a high score for large words and low scores for high numbers
    return element.length
}

fun smallNumtToScore(num: Int): Int  {
    // Return a high score for low numbers and a low score for high numbers
    return 0 - num
}

fun pointToScore(point: Point2D): Int  {
    // Return a high score for points closest to the y-axis and low scores for points furthest away
    return 0 - point.distToYAxis()
}

// TODO 1/1: Design the function topK that takes a list of
//           items, a corresponding evaluation function, and k
//           (assumed to be a postive integer), and then returns
//           the k items in the list that get the highest score
//           (if there are ties, you are free to return any of the
//           winners; if there aren't enough items in the list,
//           return as many as you can).
//
//           To help...
//           -  Your tests must cover the three examples above,
//              each time covering the empty list, a list whose
//              size is at larger than the indicated k, and a
//              non-empty list whose size is smaller than k (this
//              last test isn't necessary for (a)). Here's a data
//              type for example (c):

fun <T> topK(
    lst: List<T>,
    valueFunc: (T) -> Int,
    k: Int,
): List<T> {
    val itemScore = lst.map { ItemScore(it, valueFunc(it)) }
    val sorted = itemScore.sortedByDescending { it.score }
    val items = sorted.map { it.item }
    return items.take(k)
}

// A two-dimensional point
data class Point2D(val x: Int, val y: Int) {
    // distance to the y-axis
    fun distToYAxis(): Int = if (x > 0) x else -x
}

val p2dOrigin = Point2D(0, 0)
val p2DRight = Point2D(3, -4)
val p2DLeft = Point2D(-10, 7)

@EnabledTest
fun testPoint2D() {
    testSame(
        p2dOrigin.distToYAxis(),
        0,
        "origin/distance",
    )

    testSame(
        p2DRight.distToYAxis(),
        3,
        "right/distance",
    )

    testSame(
        p2DLeft.distToYAxis(),
        10,
        "left/distance",
    )
}

@EnabledTest
fun testTopK() {
    // a) The single longest string in a list of strings
    testSame(
        topK(listOf("dddd", "a", "bb", "ccc"), { stringToScore(it) }, 1),
        listOf("dddd"),
        "longest string",
    )

    // b) The 2 smallest numbers in a list of integers
    testSame(
        topK(listOf(1, 5, 3, 4, 2), { smallNumtToScore(it) }, 2),
        listOf(1, 2),
        "Smallest numbers",
    )

    // c) The 5 points that are closest to the y-axis
    //    (i.e., the absolute value of their x-coordinate
    //    is smallest).
    testSame(
        topK(
            listOf(
                Point2D(0, 0),
                Point2D(3, -4),
                Point2D(-10, 7),
                Point2D(1, 1),
                Point2D(2, 2),
                Point2D(3, 3),
                Point2D(4, 4),
                Point2D(5, 5),
                Point2D(6, 6),
                Point2D(7, 7),
                Point2D(8, 8),
                Point2D(9, 9),
                Point2D(10, 10),
            ),
            { pointToScore(it) },
            5,
        ),
        listOf(
            Point2D(0, 0),
            Point2D(1, 1),
            Point2D(2, 2),
            Point2D(3, -4),
            Point2D(3, 3),
        ),
        "Closest from the y-axis",
    )
}
//           - Here is a set of steps you are encouraged to follow
//             (using appropriate abstractions) in order to code up
//             your function:
//
//             1. Given your list of items, produce a
//                list of each item with its score (as given
//                by the evaluation function); here's a useful
//                type to capture that pairing:

// an association between an item and a score
data class ItemScore<T>(val item: T, val score: Int)

//             2. Sort the list of these pairs, biggest-first
//                (the sortedByDescending member function might
//                be useful here).
//
//             3. Now that you have the list in order, you no
//                longer need the scores; produce a list that
//                maintains this order, but just contains the
//                items.
//
//             4. Finally, just return the first k items of this
//                list (cough, that you "take").
//

runEnabledTests(this)
