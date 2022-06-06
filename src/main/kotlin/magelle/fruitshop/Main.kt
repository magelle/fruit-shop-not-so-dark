package magelle.fruitshop

fun main(args: Array<String>) {
    mainLoop(Basket(), generateSequence { readLine() }, ::println)
}

fun mainLoop(basket: Basket, entries: Entries, injectedPrint: Print) {
    entries.forEach { csvEntry: String ->
        parseCsv(csvEntry)
            .forEach { entry -> tryToAddFruit(entry, basket, injectedPrint) }
        injectedPrint(basket.total().toString())
    }
}

private fun tryToAddFruit(
    entry: String,
    basket: Basket,
    injectedPrint: Print
) {
    val fruit = parseFruit(entry)
    if (fruit != null) {
        basket.add(fruit)
    } else {
        injectedPrint("Error: Unknown fruits \"$entry\"")
    }
}

private fun parseCsv(entry: String): List<String> =
    entry.split(",").map(String::trim)

private fun parseFruit(fruitName: String) = Fruit.values().firstOrNull { it.name == fruitName }

typealias Entries = Sequence<String>
typealias Print = (String) -> Unit