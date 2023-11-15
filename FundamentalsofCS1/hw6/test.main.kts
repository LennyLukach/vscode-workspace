import khoury.testSame


fun foldConcat(los: List<String>): String {
    return los.fold(
         "",
         { acc, element -> acc + element },
         )
}




testSame(
     foldConcat(emptyList<String>()),
     "",
     "empty",
)

testSame(
     foldConcat(listOf("a", "b", "c")),
     "abc",
     "alpha",
)