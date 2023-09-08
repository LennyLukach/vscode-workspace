// -----------------------------------------------------------------
// Homework 1, Problem 1
// -----------------------------------------------------------------

// In a math class, the perfect squares are the squares of the
// whole numbers: 1, 4, 9, 16, ... (since 1²=1, 2²=4, 3²=9, 4²=16).
//
// Given the above description, we'd commonly say that the "first"
// perfect square is 1, the second is 4, etc. However, as we'll
// talk about later in this class, computer scientists commonly
// start counting at 0 (like a European building or elevator!).
//
// So given this context...

// TODO 1/3: Write the function kthPerfectSquare, which accepts
//           a number and outputs that perfect square. For
//           example, if the supplied number was 0, the function
//           should return 1; if the function was supplied 1, it
//           should return 4.
//
//           You can assume that the supplied number is
//           non-negative. As discussed in class, try to avoid
//           duplicating work in your code, and make sure to have
//           a short comment before the function describing what
//           it does :)
//

// returns squared value of given input
fun kthPerfectSquare(num : Int) : Int {
    var newNum = num + 1
    return newNum * newNum
}

kthPerfectSquare(1)

// TODO 2/3: Write the function perfectDescription that accepts a
//           number and provides a textual description like the
//           following...
//
//           perfectDescription(0) -> "perfect square 0 is 1"
//           perfectDescription(1) -> "perfect square 1 is 4"
//
//           Of course, make use of kthPerfectSquare, since it'll
//           be quite useful!
//

// uses kthPerfectSquare to get the perfect square and then this function 
fun perfectDescription(num: Int) : String {
    val numSquared = kthPerfectSquare(num)
    return("The perfect square of $num is $numSquared")
}


// TODO 3/3: In your main function, output to the screen some
//           uses of each of the above functions so that you have
//           confidence it works!
//
//           Note: you'll find it repetitive to write similar code
//                 for this purpose, and so we provided an
//                 approach of having a function to avoid some of
//                 the similar pieces in a helper test function -
//                 just uncomment, and feel free to adapt for
//                 other parts of the assignment! (Even this feels
//                 a bit repetitive, so stay tuned for more in the
//                 upcoming weeks!)



fun main(num : Int, expected : Int) : Boolean {
    if (kthPerfectSquare(num) == expected) {
        println(perfectDescription(num))
        return true
    }
    return false
}


main(1, 4)