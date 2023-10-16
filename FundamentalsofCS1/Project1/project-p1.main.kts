import khoury.reactConsole
import khoury.runEnabledTests
import khoury.*
import khoury.testSame
import khoury.EnabledTest
import khoury.isAnInteger
import khoury.fileReadAsList
import khoury.CapturedResult
import khoury.captureResults

// -----------------------------------------------------------------
// Project: Part 1, Summary
// -----------------------------------------------------------------

// TODO: Add a comment here saying whom you worked with (people or
// AI), and how it helped. If you did not work with anyone (which 
// would be surprising), say that.

// I worked on this project solo
// i did not help anyone with this

// You are going to design an application to allow a user to
// self-study using flash cards. In this part of the project,
// a user will...

// 1. Be prompted to choose from a menu of available flash
//    card decks; this menu will repeat until a valid
//    selection is made.
//
// 2. Proceed through each card in the selected deck,
//    one-by-one. For each card, the front is displayed,
//    and the user is allowed time to reflect; then the
//    back is displayed; and the user is asked if they
//    got the correct answer.
//
// 3. Once the deck is exhausted, the program outputs the
//    number of self-reported correct answers and ends.
//

// Of course, we'll design this program step-by-step, AND
// you've already done pieces of this in homework!!
// (Note: you are welcome to leverage your prior work and/or
// code found in the sample solutions & lecture notes.)
//

// Lastly, here are a few overall project requirements...
// - Since mutation hasn't been covered in class, your design is
//   NOT allowed to make use of mutable variables and/or lists.
// - As included in the instructions, all interactive parts of
//   this program MUST make effective use of the reactConsole
//   framework.
// - Staying consistent with our Style Guide...
//   * All functions must have:
//     a) a preceding comment specifying what it does
//     b) an associated @EnabledTest function with sufficient
//        tests using testSame
//   * All data must have:
//     a) a preceding comment specifying what it represents
//     b) associated representative examples
// - You will be evaluated on a number of criteria, including...
//   * Adherence to instructions and the Style Guide
//   * Correctly producing the functionality of the program
//   * Design decisions that include choice of tests, appropriate
//     application of list abstractions, and task/type-driven
//     decomposition of functions.
//

// -----------------------------------------------------------------
// Data design
// (Hint: see Homework 3, Problem 2)
// -----------------------------------------------------------------

// TODO 1/2: Design the data type FlashCard to represent a single
//           flash card. You should be able to represent the text
//           prompt on the front of the card as well as the text
//           answer on the back. Include at least 3 example cards
//           (which will come in handy later for tests!).

// write the code for todo 1/2 here
data class FlashCard(val front: String, val back: String)

val card1 = FlashCard("1^2 = ?", "1")
val card2 = FlashCard("2^2 = ?", "4")
val card3 = FlashCard("3^2 = ?", "9")



// TODO 2/2: Design the data type Deck to represent a deck of
//           flash cards. The deck should have a name, as well
//           as a Kotlin list of flash cards.
//
//           Include at least 2 example decks based upon the
//           card examples above.
//

// -----------------------------------------------------------------
// Generating flash cards
// -----------------------------------------------------------------

// One benefit of digital flash cards is that sometimes we can
// use code to produce cards that match a known pattern without
// having to write all the fronts/backs by hand!
//

data class Deck(val name: String, val cards: List<FlashCard>)

val deck1 = Deck("deck1", listOf(card1, card2, card3))
val deck2 = Deck("deck2", listOf(card1, card2, card3))


// TODO 1/1: Design the function perfectSquares that takes a
//           count (assumed to be positive) and produces the
//           list of flash cards that tests that number of the
//           first squares.
//
//           For example, the first three perfect squares...
//
//            1. front (1^2 = ?), back (1)
//            2. front (2^2 = ?), back (4)
//            3. front (3^2 = ?), back (9)
//
//           have been supplied as named values.
//
//           Hint: you might consider combining your
//                 kthPerfectSquare function from Homework 1
//                 with the list constructor in Homework 3.
//

val square1Front = "1^2 = ?"
val square2Front = "2^2 = ?"
val square3Front = "3^2 = ?"

val square1Back = "1"
val square2Back = "4"
val square3Back = "9"


//returns perfect square given k
fun kthPerfectSquare(k : Int): FlashCard {
    return FlashCard("${k}^2 = ?", "${k * k}")
}

val testedNum = 1

testSame(kthPerfectSquare(testedNum), FlashCard("$testedNum^2 = ?", "${testedNum * testedNum}"), "kthPerfectSquare")


//returns list of perfect squares given count
fun perfectSquares(count: Int): List<FlashCard> {
    val cards = List(count, { i -> kthPerfectSquare(i + 1) })
    return cards
}


testSame(perfectSquares(3), listOf(card1, card2, card3), "perfectSquares")



// -----------------------------------------------------------------
// Files of cards
// -----------------------------------------------------------------

// Consider a simple format for storing flash cards in a file:
// each card is a line in the file, where the front comes first,
// separated by a "pipe" character ('|'), followed by the text
// on the back of the card.
//

val charSep = "|"

// TODO 1/3: Design the function cardToString that takes a flash
//           card as input and produces a string according to the
//           specification above ("front|back"). Make sure to
//           test all your card examples!
//


//returns text on flash cards as string
fun cardToString(card: FlashCard): String {
    return "${card.front}${charSep}${card.back}"
}

testSame(cardToString(card1), "1^2 = ?|1", "cardToString")


// TODO 2/3: Design the function stringToCard that takes a string,
//           assumed to be in the format described above, and
//           produces the corresponding flash card.
//
//           Hints:
//           - look back to how we extracted data from CSV
//             (comma-separated value) files (such as in
//             Homework 3)!
//           - a great way to test: for each of your card
//             examples, pass them through the function in TODO
//             1 to convert them to a string; then, pass that
//             result to this function... you *should* get your
//             original flash card back :)
//


//returns string as card
fun stringToCard(str: String): FlashCard {
    val split = str.split(charSep)
    return FlashCard(split[0], split[1])
}

testSame(stringToCard("1^2 = ?|1"), card1, "stringToCard")

// TODO 3/3: Design the function readCardsFile that takes a path
//           to a file and produces the corresponding list of
//           flash cards found in the file.
//
//           If the file does not exist, return an empty list.
//           Otherwise, you can assume that every line is
//           formatted in the string format we just worked with.
//
//           Hint:
//           - Think about how HW3-P1 effectively used an
//             abstraction to process all the lines in a
//             file assuming a known pattern.
//           - We've provided an "example.txt" file that you can
//             use for testing if you'd like; also make sure to
//             test your function when the supplied file does not
//             exist!
//

// -----------------------------------------------------------------
// Processing a self-report
// (Hint: see Homework 2)
// -----------------------------------------------------------------

// In our program, we will ask for a self-report as to whether
// the user got the correct answer for a card, SO...

// reads the file path for the card that the user wants to import from outside the function
fun readCardsFile(path: String): List<FlashCard> {
    val lines = fileReadAsList(path)
    val cards = List(lines.size, { i -> stringToCard(lines[i]) })
    return cards
}

testSame(readCardsFile("example.txt"), listOf(FlashCard("front 1", "back 1"), FlashCard("front 2", "back 2")), "readCardsFile")

// TODO 1/1: Finish designing the function isPositive that
//           determines if the supplied string starts with
//           the letter "y" (either upper or lowercase).
//
//           You've been supplied with a number of tests - make
//           sure you understand what they are doing!
//

// check if it string starts with y
fun isPositive(str: String): Boolean {
    return str.startsWith("y", ignoreCase = true)
}

//test to see if ifPostive works in all appropriate cases
@EnabledTest
fun testIsPositive() {
    fun helpTest(str: String, expected: Boolean) {
        testSame(isPositive(str), expected, str)
    }

    helpTest("yes", true)
    helpTest("Yes", true)
    helpTest("YES", true)
    helpTest("yup", true)

    helpTest("nope", false)
    helpTest("NO", false)
    helpTest("nah", false)
    helpTest("not a chance", false)

    // should pass,
    // despite doing the wrong thing
    helpTest("indeed", false)
}

// -----------------------------------------------------------------
// Choosing a deck from a menu
// -----------------------------------------------------------------

// Now let's work on providing a menu of decks from which a user
// can choose what they want to study.

// TODO 1/2: Finish design the function choicesToText that takes
//           a list of strings (assumed to be non-empty) and
//           produces the textual representation of a menu of
//           those options.
//
//           For example, given...
//
//           ["a", "b", "c"]
//
//           The menu would be...
//
//           "1. a
//            2. b
//            3. c
//
//            Enter your choice"
//
//            As you have probably guessed, this will be a key
//            piece of our rendering function :)
//
//            Hints:
//            - Think back to Homework 3 when we used a list
//              constructor to generate list elements based
//              upon an index.
//            - If you can produce a list of strings, the
//              linesToString function in the Khoury library
//              will bring them together into a single string.
//            - Make sure to understand the supplied tests!
//

val promptMenu = "Enter your choice"


// writes the choices in the console as text as a menu
fun choicesToText(choices: List<String>): String {
    val lines = List(choices.size, { i -> "${i + 1}. ${choices[i]}"}) + listOf("", "$promptMenu")
    return linesToString(lines)
}

testSame(choicesToText(listOf("a", "b", "c")), linesToString("1. a", "2. b", "3. c", "", promptMenu), "choicesToText")

// tests to see if choicesToText works in all appropriate cases
@EnabledTest
fun testChoicesToText() {
    val optA = "apple"
    val optB = "banana"
    val optC = "carrot"

    testSame(
        choicesToText(listOf(optA)),
        linesToString(
            "1. $optA",
            "",
            promptMenu,
        ),
        "one"
    )

    testSame(
        choicesToText(listOf(optA, optB, optC)),
        linesToString(
            "1. $optA",
            "2. $optB",
            "3. $optC",
            "",
            promptMenu,
        ),
        "three"
    )
}

// TODO 2/2: Finish designing the program chooseOption that takes
//           a list of decks, produces a corresponding numbered
//           menu (1-# of decks, each showing its name), and
//           returns the deck corresponding to the number entered.
//           (Of course, keep displaying the menu until a valid
//           number is entered.)
//
//           Hints:
//            - Review the "Valid Number Example" of reactConsole
//              as one example of how to validate input. In this
//              case, however, since we know that we have a valid
//              range of integers, we can simplify the state
//              representation significantly :)
//            - To help you get started, the chooseOption function
//              has been written, but you must complete the helper
//              functions; look to the comments below for guidance.
//              You can then play "signature detective" to figure
//              out the parameters/return type of the functions you
//              need to write :)
//            - Lastly, as always, don't forget to sufficiently
//              test all the functions you write in this problem!
//

// a program to allow the user to interactively select
// a deck from the supplied, non-empty list of decks


//gets the deck name
fun getDeckName(deck: Deck): String {
    return deck.name
}

testSame(getDeckName(deck1), "deck1", "getDeckName")


// keeps the inputted value for a deck if its withtin the appropriate range
fun keepIfValid(kbInput: String, validIndices: IntRange): Int {
    return if (isAnInteger(kbInput)) {
        val index = kbInput.toInt() - 1
        if (index in validIndices) index else -1
    } else {
        -1
    }
}

testSame(keepIfValid("1", 0..2), 0, "keepIfValid")

// returns the chosen deck
fun choiceAnnouncement(deckName: String): String {
    return "you chose: $deckName"
}

testSame(choiceAnnouncement("deck1"), "you chose: deck1", "choiceAnnouncement")

// function that goes through the deck and prints each line and choice
fun chooseOption(decks: List<Deck>): Deck {
    // since the event handlers will need some info about
    // the supplied decks, the functions inside
    // chooseOption provide info about them while the
    // parameter is in scope

    // TODO: Above chooseOption, design the function
    //       getDeckName, which returns the name of
    //       a supplied deck.
    fun renderDeckOptions(state: Int): String {
        return choicesToText(decks.map(::getDeckName))
    }

    // TODO: Above chooseOption, design the function
    //       keepIfValid, that takes the typed input
    //       as a string, as well as the valid
    //       indices of the decks; note that the list indices
    //       will be in the range [0, size), whereas the
    //       user will see and work with [1, size].
    //
    //       If the user did not type a valid integer,
    //       or not one in [1, size], return -1; otherwise
    //       return the string converted to an integer, but
    //       subtract 1, which makes it a valid list index.
    fun transitionOptionChoice(ignoredState: Int, kbInput: String): Int {
        return keepIfValid(kbInput, decks.indices)
    }

    // TODO: nothing, but understand this :)
    fun validChoiceEntered(state: Int): Boolean {
        return state in decks.indices
    }

    // TODO: Above chooseOption, design the function
    //       choiceAnnouncement that takes the selected
    //       deck name and returns an announcement that
    //       makes you happy. For a simple example, given
    //       "fundies" as the chosen deck name, you might
    //       return "you chose: fundies"
    fun renderChoice(state: Int): String {
        return choiceAnnouncement(getDeckName(decks[state]))
    }

    return decks[reactConsole(
        initialState = -1,
        stateToText = ::renderDeckOptions,
        nextState = ::transitionOptionChoice,
        isTerminalState = ::validChoiceEntered,
        terminalStateToText = ::renderChoice,
    )]
}

// -----------------------------------------------------------------
// Studying a deck
// -----------------------------------------------------------------

// Now let's design a program to allow a user to study through a
// supplied deck of flash cards.

// TODO 1/2: Design the data type StudyState to keep track of...
//           - which card you are currently studying in the deck
//           - are you looking at the front or back
//           - how many correct answers have been self-reported
//             thus far
//
//           Create sufficient examples so that you convince
//           yourself that you can represent any situation that
//           might arise when studying a deck.
//
//           Hints:
//           - Look back to the reactConsole problems in HW2 and
//             HW3; the former involved keeping track of a count
//             of loops (similar to the count of correct answers),
//             and the latter involved options for keeping track
//             of where you are in a list with reactConsole.
//

data class StudyState(val currentDeck: Deck, val currentCard: Int, val isFront: Boolean, val correctAnswers: Int)

val studyState1 = StudyState(deck1, 1,true, 0)
val studyState2 = StudyState(deck1, 1, false, 1)
val studyState3= StudyState(deck1, 1, false, 2)


// TODO 2/2: Now, using reactConsole, design the program studyDeck
//           that for each card in a supplied deck, allows the
//           user to...
//
//           1. see the front (pause and think)
//           2. see the back
//           3. respond as to whether they got the answer
//
//           At the end, the user is told how many they self-
//           reported as correct (and this number is returned).
//
//           You have been supplied some prompts for steps #1
//           and #2 - feel free to change them if you'd like :)
//
//           Suggestions...
//           - Review the reactConsole videos/examples
//           - Start with studyDeck:
//             * write some tests to convince yourself you know
//               what your program is supposed to do!
//             * figure out how you'll create the initial state
//             * give names to the handlers you'll need
//             * how will you return the number correct?
//             * now comment-out this function, so that you can
//               design/test the handlers without interference :)
//           - For each handler...
//             * Play signature detective: based upon how it's
//               being used with reactConsole, what data will it
//               be given and what does it produce?
//             * Write some tests to convince yourself you know
//               its job.
//             * Write the code and don't move on till your tests
//               pass.
//            - Suggested ordering...
//              1. Am I done studying yet?
//              2. Rendering
//                 - It's a bit simpler to have a separate
//                   function for the terminal state.
//                 - The linesToString function is your friend to
//                   combine the card with the prompts.
//                 - Think about good decomposition when making
//                   the decision about front vs back content.
//              3. Transition
//                 - Start with the two main situations
//                   you'll find yourself in...
//                   > front->back
//                   > back->front
//                 - Then let a helper figure out how to handle
//                   the details of self-report
//
//            You've got this :-)
//

val studyThink = "Think of the result? Press enter to continue"
val studyCheck = "Correct? (Y)es/(N)o"


// checks to see if the user is done studying
fun isDoneStudying(state: StudyState): Boolean {
    return state.currentCard == state.currentDeck.cards.size
}

testSame(isDoneStudying(studyState1), false, "isDoneStudying")

// renders the study state
fun renderStudy(state: StudyState): String {
    val card = state.currentDeck.cards[state.currentCard]
    return if (state.isFront) {
        linesToString(card.front, "", studyThink)
    } else {
        linesToString(card.back, "", studyCheck)
    }
}

testSame(renderStudy(studyState1), linesToString("2^2 = ?", "", studyThink), "renderStudy")


// transitions the study state
fun transitionStudy(state: StudyState, kbInput: String): StudyState {
    val card = state.currentDeck.cards[state.currentCard]
    return if (state.isFront) {
        StudyState(state.currentDeck, state.currentCard, false, state.correctAnswers)
    } else {
        val correct = isPositive(kbInput)
        val newCorrectAnswers = if (correct) state.correctAnswers + 1 else state.correctAnswers
        StudyState(state.currentDeck, state.currentCard + 1, true, newCorrectAnswers)
    }
}

val deckTest = Deck("deck1", listOf(FlashCard("front1", "back1"), FlashCard("front2", "back2")))
val studyStateTest1 = StudyState(deckTest, 0, true, 0)
val studyStateTest2 = StudyState(deckTest, 0, false, 0)


testSame(transitionStudy(studyState1, "yes").isFront, false, "transitionStudy 1")
testSame(transitionStudy(studyState1, "yes").correctAnswers, 0, "transitionStudy 1")
testSame(transitionStudy(studyState1, "yes").currentCard, 1, "transitionStudy 1")
testSame(transitionStudy(studyState1, "yes").currentDeck, deck1, "transitionStudy 1")

testSame(transitionStudy(studyState2, "yes").isFront, true, "transitionStudy 2")
testSame(transitionStudy(studyState2, "yes").correctAnswers, 2, "transitionStudy 2")
testSame(transitionStudy(studyState2, "yes").currentCard, 2, "transitionStudy 2")
testSame(transitionStudy(studyState2, "yes").currentDeck, deck1, "transitionStudy 2")

// renders the terminal state
fun renderStudyTerminal(state: StudyState): String {
    return "You got ${state.correctAnswers} correct"
}

testSame(renderStudyTerminal(studyState1), "You got 0 correct", "renderStudyTerminal")


// allows user to interact with the deck and then retursn the number of correct answers
fun studyDeck(deck: Deck): Int {
    return reactConsole(
        initialState = StudyState(deck, 0, true, 0),
        stateToText = ::renderStudy,
        nextState = ::transitionStudy,
        isTerminalState = ::isDoneStudying,
        terminalStateToText = ::renderStudyTerminal,
    ).correctAnswers
}

@EnabledTest
fun testStudyDeck() {
    fun helpTest(deck: Deck): () -> Unit {
        fun studyMyDeck() {
            studyDeck(deck)
        }

        return ::studyMyDeck
    }

    testSame(
        captureResults(
            helpTest(deck1),
            "y",
            "y",
            "y",
        ),
        CapturedResult(
            Unit,
            "1^2 = ?",
            "",
            "Think of the result? Press enter to continue",
            "1",
            "",
            "Correct? (Y)es/(N)o",
            "2^2 = ?",
            "",
            "Think of the result? Press enter to continue",
            "4",
            "",
            "Correct? (Y)es/(N)o",
            "3^2 = ?",
            "",
            "Think of the result? Press enter to continue",
            "9",
            "",
            "Correct? (Y)es/(N)o",
            "You got 1 correct",
        ),
        "deck1",
    )
}


// -----------------------------------------------------------------
// Final app!
// -----------------------------------------------------------------

// Now you just get to put this all together 💃

// TODO 1/1: Design the function chooseAndStudy, where you'll
//           follow the comments in the supplied code to leverage
//           your prior work to allow the user to choose a deck,
//           study it, and return the number of correct self-
//           reports.
//
//           Your deck options MUST include at least one from each
//           of the following categories...
//
//           - Coded by hand (such as an example in data design)
//           - Read from a file (ala readCardsFile)
//           - Generated by code (ala perfectSquares)
//
//           Note: while this is an interactive program, you won't
//                 directly use reactConsole - instead, just call
//                 the programs you already designed above :)
//
//           And of course, don't forget to test at least two runs
//           of this completed program!
//
//           (And, consider adding this to main so you can see the
//           results of all your hard work so far this semester!)
//


// lets the user choose a deck and study it,
// returning the number self-reported correct
fun chooseAndStudy(): Int {
    // 1. Construct a list of options
    // (ala the instructions above)

    val deckOptions = listOf(
        // TODO: at least...
        // deck from file via readCardsFile,
        // deck from code via perfectSquares
        // deck hand-coded
        Deck("deck1", readCardsFile("example.txt")),
        Deck("deck2", perfectSquares(3)),
        Deck("deck3", listOf(card1, card2, card3))
    )

    // 2. Use chooseOption to let the user
    //    select a deck
    val deckChosen = chooseOption(deckOptions)

    // 3. Let the user study, return the
    //    number correctly answered
    return studyDeck(deckChosen)
}

@EnabledTest
fun testChooseAndStudy() {
    fun helpTest(): () -> Unit {
        fun chooseAndStudyMyDecks() {
            chooseAndStudy()
        }

        return ::chooseAndStudyMyDecks
    }

    testSame(
        captureResults(
            helpTest(),
            "2",
            "y",
            "y",
            "y",
        ),
        CapturedResult(
            Unit,
            "1. deck1",
            "2. deck2",
            "3. deck3",
            "",
            "Enter your choice",
            "you chose: deck2",
            "1^2 = ?",
            "",
            "Think of the result? Press enter to continue",
            "1",
            "",
            "Correct? (Y)es/(N)o",
            "2^2 = ?",
            "",
            "Think of the result? Press enter to continue",
            "4",
            "",
            "Correct? (Y)es/(N)o",
            "3^2 = ?",
            "",
            "Think of the result? Press enter to continue",
            "9",
            "",
            "Correct? (Y)es/(N)o",
            "You got 1 correct",
        ),
        "chooseAndStudy deck2",
    )
    testSame(
        captureResults(
            helpTest(),
            "3",
            "y",
            "y",
            "n",
        ),
        CapturedResult(
            Unit,
            "1. deck1",
            "2. deck2",
            "3. deck3",
            "",
            "Enter your choice",
            "you chose: deck3",
            "1^2 = ?",
            "",
            "Think of the result? Press enter to continue",
            "1",
            "",
            "Correct? (Y)es/(N)o",
            "2^2 = ?",
            "",
            "Think of the result? Press enter to continue",
            "4",
            "",
            "Correct? (Y)es/(N)o",
            "3^2 = ?",
            "",
            "Think of the result? Press enter to continue",
            "9",
            "",
            "Correct? (Y)es/(N)o",
            "You got 1 correct",
        ),
        "chooseAndStudy deck3",
    )
}




// -----------------------------------------------------------------

fun main() {
    chooseAndStudy()
}

runEnabledTests(this)
main()
