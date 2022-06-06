package magelle.fruitshop

class Basket {
    private val items = mutableListOf<Fruit>()

    fun add(fruit: Fruit) {
        items.add(fruit)
    }

    fun total(): Price =
        items.sumOf { it.price() } - discounts()

    private fun discounts() = cerisesDiscount(items) + bananesDiscount(items)

    private fun cerisesDiscount(items: List<Fruit>): Price =
        items.count { it == (Fruit.Cerises) } / 2 * 30

    private fun bananesDiscount(items: List<Fruit>): Price =
        items.count { it == (Fruit.Bananes) } / 2 * Fruit.Bananes.price()

}

private fun Fruit.price(): Price =
    when (this) {
        Fruit.Pommes -> 100
        Fruit.Bananes -> 150
        Fruit.Cerises -> 75
    }


enum class Fruit {
    Pommes,
    Bananes,
    Cerises
}

typealias Price = Int