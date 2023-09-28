// fun product(list : LinkedListNumbers) : Int {
//     return when(list) {
//         is LinkedListNumbers.Empty -> return 1
//         is LinkedListNumbers.Node -> list.data * product(list.next)
//     }
// }


val numList = List<Int>(4) { it + 1}

println(numList)

fun betterProduct(list : List<Int>) : Int {
    var product = 1
    for (x in list) {
        product *= x
    }
    return product
}

betterProduct(numList)

fun betterProduct2(list : List<Int>) : Int {
    return list.fold(1) { acc, x -> acc * x }
}