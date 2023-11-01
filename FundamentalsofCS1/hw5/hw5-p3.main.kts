import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// -----------------------------------------------------------------
// Homework 5, Problem 3
// -----------------------------------------------------------------

// In this problem you'll practice designing a data class that has
// methods, making your first upgrade to the project.

// TODO 1/1: Design the data type TaggedFlashCard to represent a
//           single flash card.
//
//           You should be able to represent the text prompt on
//           the front of the card, the text answer on the back,
//           as well as any number of textual tags (such as "hard"
//           or "science" -- this shouldn't come from any fixed
//           set of options, but truly open to however someone
//           wishes to categorize their cards).
//
//           Each card should have two convenience methods:
//           - isTagged, which determines if the card has a
//             supplied tag (e.g., has this card been tagged
//             as "hard"?)
//           - fileFormat, which produces a textual representation
//             of the card as "front|back|tag1,tag2,..."; that is
//             all three parts of the card separated with the pipe
//             ('|') character, and further separate any tags with
//             a comma (',')
//
//           Include *at least* 3 example cards, and make sure to
//           test the methods (in a single testTaggedFlashCard
//           function that has been annotated as @EnabledTest).
//

data class TaggedFlashCard(val front: String, val back: String, val tags: List<String>) {
    fun isTagged(tag: String): Boolean = tags.contains(tag)

    fun fileFormat(): String = "$front$sepCard$back$sepCard${tags.joinToString(sepTag)}"
}

val sepCard = "|"
val sepTag = ","
// (just useful values for
// the separation characters)

val card1 = TaggedFlashCard("What is the capital of MA?", "Boston", listOf("geography", "hard"))
val card2 = TaggedFlashCard("What is the capital of NY?", "Albany", listOf("geography"))
val card3 = TaggedFlashCard("What is the capital of CA?", "Sacramento", listOf("geography", "hard", "state"))

@EnabledTest
fun testTaggedFlashCard() {
    testSame(
        card1.isTagged("hard"),
        true,
        "card1.isTagged(\"hard\")",
    )

    testSame(
        card1.isTagged("easy"),
        false,
        "card1.isTagged(\"easy\")",
    )

    testSame(
        card2.isTagged("geography"),
        true,
        "card2.isTagged(\"geography\")",
    )

    testSame(
        card2.isTagged("hard"),
        false,
        "card2.isTagged(\"hard\")",
    )

    testSame(
        card3.isTagged("state"),
        true,
        "card3.isTagged(\"state\")",
    )

    testSame(
        card3.isTagged("hard"),
        true,
        "card3.isTagged(\"hard\")",
    )

    testSame(
        card3.isTagged("easy"),
        false,
        "card3.isTagged(\"easy\")",
    )

    testSame(
        card1.fileFormat(),
        "What is the capital of MA?|Boston|geography,hard",
        "card1.fileFormat()",
    )

    testSame(
        card2.fileFormat(),
        "What is the capital of NY?|Albany|geography",
        "card2.fileFormat()",
    )

    testSame(
        card3.fileFormat(),
        "What is the capital of CA?|Sacramento|geography,hard,state",
        "card3.fileFormat()",
    )
}

runEnabledTests(this)
