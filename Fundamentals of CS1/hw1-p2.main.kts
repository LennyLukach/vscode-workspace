// -----------------------------------------------------------------
// Homework 1, Problem 2
// -----------------------------------------------------------------

// For any word of at least one character that starts with a
// letter, let's say that its "bingo word" is the uppercase
// version of the first letter, followed by a space, and then
// followed by the number of characters in the word. For example,
// the bingo word of "bingo" is "B 5" and the bingo word of "Win"
// is "W 3".

// TODO 1/1: Write the function bingoWord that takes a string as
//           an argument and returns its bingo word. You may
//           assume that the argument is a valid word as described
//           above. Now try it out a couple times by outputting a
//           word and its corresponding result in your main.
//
//           Reminder: given a string s, s.uppercase() converts it
//                     to caps and s.first() gets the first letter.
//

// gets first letter of a word, capitalizes it, prints it out and the lenght of the original string
fun bingoWord(word : String) : String {
    var firstLetter = word.first()
    var newWord : String = firstLetter.uppercase() + word.length
    return newWord

}

bingoWord("hello")