package magelle.fruitshop

class Basket {
    private val items = mutableListOf<Fruit>()

    fun add(fruit: Fruit) {
        items.add(fruit)
    }

    fun total(): Price =
        items.sumOf { it.price() } - cerisesDiscount(items)

    private fun cerisesDiscount(items: List<Fruit>): Price =
        items.count { it == (Fruit.Cerises) } / 2 * 20

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