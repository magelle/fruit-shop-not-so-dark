package magelle.fruitshop

class Basket {
    private val items = mutableListOf<Fruit>()

    fun add(fruit: Fruit) {
        items.add(fruit)
    }

    fun total(): Price =
        items.sumOf { it.price() } - applyDiscounts()

    private fun applyDiscounts() = discounts.sumOf { it(items) }
}

fun Fruit.price(): Price =
    when (this) {
        Fruit.Pommes, Fruit.Apples, Fruit.Mele -> 100
        Fruit.Bananes -> 150
        Fruit.Cerises -> 75
    }


enum class Fruit {
    Pommes,
    Bananes,
    Cerises,
    Apples,
    Mele
}

typealias Price = Int