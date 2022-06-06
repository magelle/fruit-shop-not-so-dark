package magelle.fruitshop

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class BasketTest {

    @Test
    fun `fruits have prices`() {
        assertFruitPrice("Pommes", 100)
        assertFruitPrice("Bananes", 150)
        assertFruitPrice("Cerises", 75)
    }

    @Test
    fun `should get the total of the basket`() {
        val basket = Basket()

        basket.add("Pommes")
        basket.add("Pommes")
        basket.add("Cerises")

        assertThat(basket.total()).isEqualTo(275)
    }

    private fun assertFruitPrice(fruit: Fruit, price: Price) {
        val basket = Basket()
        basket.add(fruit)
        assertThat(basket.total()).isEqualTo(price)
    }

}
