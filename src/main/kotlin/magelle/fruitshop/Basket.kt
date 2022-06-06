package magelle.fruitshop

class Basket {
    private val items = mutableListOf<Fruit>()

    fun add(fruit: Fruit) {
        items.add(fruit)
    }

    fun total(): Price =
        items.sumOf { it.price() }

}

private fun Fruit.price(): Price =
    when (this) {
        "Pommes" -> 100
        "Bananes" -> 150
        "Cerises" -> 75
        else -> throw IllegalStateException("Unknown item $this")
    }


typealias Fruit = String
typealias Price = Int