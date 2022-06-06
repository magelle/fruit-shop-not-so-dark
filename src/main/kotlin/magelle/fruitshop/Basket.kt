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
        Fruit.Pommes -> 100
        Fruit.Bananes -> 150
        Fruit.Cerises -> 75
        else -> throw IllegalStateException("Unknown item $this")
    }


enum class Fruit {
    Pommes,
    Bananes,
    Cerises
}

typealias Price = Int