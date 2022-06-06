package magelle.fruitshop

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class BasketTest {
    private val bananesPrice = 150

    @Test
    fun `fruits have prices`() {
        assertBasketPrice(listOf(Fruit.Pommes), 100)
        assertBasketPrice(listOf(Fruit.Bananes), bananesPrice)
        assertBasketPrice(listOf(Fruit.Cerises), 75)
    }

    @Test
    fun `should get the total of the basket`() {
        assertBasketPrice(listOf(Fruit.Pommes, Fruit.Pommes, Fruit.Cerises), 275)
    }

    @Test
    fun `a batch of 2 cerises gives a discount of 20`() {
        val cerisesDiscount = 30
        assertBasketPrice(List(1) { Fruit.Cerises }, 75)
        assertBasketPrice(List(2) { Fruit.Cerises }, (75 * 2) - cerisesDiscount)
        assertBasketPrice(List(3) { Fruit.Cerises }, (75 * 3) - cerisesDiscount)
        assertBasketPrice(List(4) { Fruit.Cerises }, (75 * 4) - (cerisesDiscount * 2))
    }

    @Test
    fun `a batch of 2 bananes gives one free`() {
        assertBasketPrice(List(1) { Fruit.Bananes }, bananesPrice)
        assertBasketPrice(List(2) { Fruit.Bananes }, (bananesPrice * 2) - bananesPrice)
        assertBasketPrice(List(3) { Fruit.Bananes }, (bananesPrice * 3) - bananesPrice)
        assertBasketPrice(List(4) { Fruit.Bananes }, (bananesPrice * 4) - (bananesPrice * 2))
    }

    private fun assertBasketPrice(fruits: List<Fruit>, price: Int) {
        val basket = Basket()

        fruits.forEach(basket::add)

        assertThat(basket.total()).isEqualTo(price)
    }

}
