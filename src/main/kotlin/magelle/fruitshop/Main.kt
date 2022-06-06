package magelle.fruitshop

fun main(args: Array<String>) {
    mainLoop(Basket(), generateSequence { readLine() }, ::println)
}

fun mainLoop(basket: Basket, entries: Entries, injectedPrint: Print) {
    entries.forEach { fruit: String ->
        basket.add(fruit)
        injectedPrint(basket.total().toString())
    }
}

typealias Entries = Sequence<String>
typealias Print = (String) -> Unit