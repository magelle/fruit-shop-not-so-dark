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

private fun Fruit.price(): Price =
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
typealias Discount = (List<Fruit>) -> Price

fun buildDiscount(discount: Price, every: Int, fruit: Fruit) = { items: List<Fruit> ->
    items.count { it == fruit } / every * discount
}

val cerisesDiscount: Discount = buildDiscount(discount = 20, every = 2, fruit = Fruit.Cerises)
val bananesDiscount: Discount = buildDiscount(discount = Fruit.Bananes.price(), every = 2, fruit = Fruit.Bananes)
val applesDiscount: Discount = buildDiscount(discount = 100, every = 3, fruit = Fruit.Apples)
val meleDiscount: Discount = buildDiscount(discount = 100, every = 2, fruit = Fruit.Mele)
val anyPommesDiscount: Discount = {items: List<Fruit> ->
    items.count {
        listOf(Fruit.Apples, Fruit.Pommes, Fruit.Mele).contains(it)
    } / 4 * 100
}
val fiveFruitsDiscount: Discount = {items: List<Fruit> ->
    items.count() / 5 * 200
}

private val discounts = listOf(
    cerisesDiscount,
    bananesDiscount,
    applesDiscount,
    meleDiscount,
    anyPommesDiscount,
    fiveFruitsDiscount
)