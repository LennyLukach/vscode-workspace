import khoury.testSame



fun foldFlatten(llos: List<List<String>>): List<String> {
     return llos.fold(emptyList()) {acc, element -> acc + element}



testSame(
     foldFlatten(emptyList<List<String>>()),
     emptyList<String>(),
)
testSame(
     foldFlatten(listOf(
          listOf("a", "bb"),
          listOf("c"),
          listOf()
     )),
     listOf("a", "bb", "c"),
)    