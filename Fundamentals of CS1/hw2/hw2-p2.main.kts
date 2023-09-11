// -----------------------------------------------------------------
// Homework 2, Problem 2
// -----------------------------------------------------------------

import khoury.testSame
import khoury.EnabledTest
import khoury.runEnabledTests   

// TODO 1/1: Finish designing the function wakeupTime that asks at
//           the console what hour a person likes to wake up in
//           the morning. Depending on their response, your
//           function provides a particular response. You should
//           NOT use reactConsole for this problem.
//
//           To help, you have been supplied a set of tests --
//           uncomment these and once your function passes, you
//           should be in good shape!
//
//           If you are having trouble, try calling your function
//           from main and debug!
//

val WAKEUP_PROMPT = "What hour in the morning (1-11) do you like to wakeup?"
val WAKEUP_NOT_A_NUMBER = "You did not enter a number :("
val WAKEUP_NOT_IN_RANGE = "You did not enter an hour from 1-11am"
val WAKEUP_EARLY = "Before 8am? Early bird catches the worm!"
val WAKEUP_OTHER = "8am or later? Coffee time!"

// @EnabledTest
// fun testWakeupTime() {
//     // helps to test, given what is typed at the console
//     // and what the expected output should be
//     fun testHelp(consoleIn: String, expectedOut: String) {
//         testSame(
//             captureResults(::wakeupTime, consoleIn),
//             CapturedResult(
//                 Unit,
//                 WAKEUP_PROMPT,
//                 expectedOut,
//             ),
//             consoleIn,
//         )
//     }

//     testHelp("howdy", WAKEUP_NOT_A_NUMBER)
//     testHelp("0", WAKEUP_NOT_IN_RANGE)
//     testHelp("12", WAKEUP_NOT_IN_RANGE)
//     testHelp("5", WAKEUP_EARLY)
//     testHelp("8", WAKEUP_OTHER)
//     testHelp("11", WAKEUP_OTHER)
// }
