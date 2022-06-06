package magelle.fruitshop

fun main(args: Array<String>) {
    mainLoop(Basket(), generateSequence { readLine() }, ::println)
}

fun mainLoop(basket: Basket, entries: Entries, injectedPrint: Print) {
    entries.forEach { fruitName: String ->
        val fruit = parseFruit(fruitName)

        if (fruit != null) {
            basket.add(fruit)
            injectedPrint(basket.total().toString())
        } else {
            injectedPrint("Error: Unknown fruits \"$fruitName\"")
        }
    }
}

private fun parseFruit(fruitName: String) = Fruit.
        values().firstOrNull { it.name == fruitName }

typealias Entries = Sequence<String>
typealias Print = (String) -> Unit