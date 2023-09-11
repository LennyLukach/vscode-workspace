// -----------------------------------------------------------------
// Homework 2, Problem 1
// -----------------------------------------------------------------

// TODO 1/1: Design the predicate startsWithY that determines if
//           the supplied string starts with the letter "y"
//           (either upper or lowercase).
//
//           Hints:
//            - The string.startsWith(prefix) function will help
//              evaluate the prefix (even if the string is too
//              short).
//            - The string.lowercase/uppercase() functions help
//              you not worry about case.
//            - Remember that "designing" a function means to
//              document and test it!
//

fun startsWithY(word : String) : Boolean {
    var lowerWord = word.lowercase()
    if (lowerWord.startsWith("y")) {
        return true
    }

    return false
}

println(startsWithY("Hello"))