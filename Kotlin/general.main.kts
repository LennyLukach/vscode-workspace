
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