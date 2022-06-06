package magelle.fruitshop

class Basket {
    private val items = mutableListOf<Fruit>()

    fun add(fruit: Fruit) {
        items.add(fruit)
    }

    fun total(): Price =
        items.asSequence()
            .sumOf { it.price() }

}

private fun Fruit.price(): Price =
    when (this) {
        "Pomme" -> 100
        "Bananes" -> 150
        "Cerise" -> 75
        else -> throw IllegalStateException("Unknown item $this")
    }


typealias Fruit = String
typealias Price = Int