import java.io.File

val words = File("sgb-words.txt").readLines()


val chosenWord = words.random()
val letterBank = mutableListOf<Char>()
val displayedWord = chosenWord.map { "_" }.toMutableList()
val bannedWords = mutableListOf<Char>()

println("Welcome to Wordle!")
//println(chosenWord)
while (!(displayedWord.joinToString("") == chosenWord)){
    println("The word is: ${displayedWord.joinToString(" ")}")
    println("These letters are somewhere in the word: ${letterBank.joinToString(" ")}\n")
    println("Guess a word: ")
    val guess = readLine()!!
    if (guess.length != chosenWord.length){
        println("you broke the rules asshole. kys")
        continue
    }
    else {
        for (i in 0 until chosenWord.length) {
            if (chosenWord[i] == guess[i]) {
                displayedWord[i] = guess[i].toString()
            }
        }
        for (i in 0 until guess.length) {
            if (chosenWord.contains(guess[i]) && !bannedWords.contains(guess[i])) {
                letterBank.add(guess[i])
                bannedWords.add(guess[i])
            }
        }
    }

}