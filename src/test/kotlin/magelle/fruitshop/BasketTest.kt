package magelle.fruitshop

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class BasketTest {

    @Test
    fun `fruits have prices`() {
        assertBasketPrice(listOf(Fruit.Pommes), 100)
        assertBasketPrice(listOf(Fruit.Bananes), 150)
        assertBasketPrice(listOf(Fruit.Cerises), 75)
    }

    @Test
    fun `should get the total of the basket`() {
        assertBasketPrice(listOf(Fruit.Pommes, Fruit.Pommes, Fruit.Cerises), 275)
    }

    @Test
    fun `a batch of 2 cerises gives a discount of 20`() {
        assertBasketPrice(List(1) { Fruit.Cerises }, 75)
        assertBasketPrice(List(2) { Fruit.Cerises }, (75 * 2) - 20)
        assertBasketPrice(List(3) { Fruit.Cerises }, (75 * 3) - 20)
        assertBasketPrice(List(4) { Fruit.Cerises }, (75 * 4) - (20 * 2))
    }

    private fun assertBasketPrice(fruits: List<Fruit>, price: Int) {
        val basket = Basket()

        fruits.forEach(basket::add)

        assertThat(basket.total()).isEqualTo(price)
    }

}
