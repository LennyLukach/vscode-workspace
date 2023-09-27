// -----------------------------------------------------------------
// Homework 3, Problem 2
// -----------------------------------------------------------------

import khoury.EnabledTest
import khoury.testSame

// TODO 1/4: Design the data type FlashCard to represent a single
//           flash card. You should be able to represent the text
//           prompt on the front of the card as well as the text
//           answer on the back. Include at least 3 example cards
//           (which will come in handy later for tests!).
//

data class FlashCard(val word: String, val definition: String)

val fc1 = FlashCard("Hello", "A greeting")
val fc2 = FlashCard("Goodbye", "A farewell")
val fc3 = FlashCard("How are you?", "Question")

// TODO 2/4: Design the data type Deck to represent a deck of
//           flash cards. The deck should have a name, as well
//           as a sequence of flash cards.
//
//           Include at least 2 example decks based upon the
//           card examples above.

data class Deck(val name: String, val cards: List<FlashCard>)
val deck1 = Deck("Greetings", listOf(fc1, fc2))
val deck2 = Deck("Questions", listOf(fc3))

// TODO 3/4: Design the predicate areAllOneWordAnswers that
//           determines if the backs of all the cards in a deck
//           are a single word (i.e., have no spaces, which
//           includes a card with a blank back).
//
//           Hint: hidden in the name of this function is a
//                 reminder of a useful list function to use :)
//

fun areAllOneWordAnswers(deck: Deck): Boolean {
    return deck.cards.all({ it.definition.contains(" ") == false })
}

// TODO 4/4: Design the predicate anyContainsPhrase that determines
//           if any of the cards in a deck contain the supplied
//           phrase.
//
//           Hints:
//           - string1.contains(string2) will be quite useful
//             here :)
//           - Again, the name of this function hints at a useful
//             list function we learned!
//

fun anyContainsPhrase(
    deck: Deck,
    phrase: String,
): Boolean {
    return deck.cards.any({ it.definition.contains(phrase) })
}

// write test functions using testSame()

@EnabledTest
fun testAreAllOneWordAnswers() {
    testSame(areAllOneWordAnswers(deck1), false)
    testSame(areAllOneWordAnswers(deck2), true)
}

@EnabledTest
fun testAnyContainsPhrase() {
    testSame(anyContainsPhrase(deck1, "greeting"), true)
    testSame(anyContainsPhrase(deck2, "greeting"), false)
}

testAnyContainsPhrase()
testAreAllOneWordAnswers()
