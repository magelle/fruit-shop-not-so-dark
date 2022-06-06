package magelle.fruitshop

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class BasketTest {

    @Test
    fun `fruits have prices`() {
        assertFruitPrice(Fruit.Pommes, 100)
        assertFruitPrice(Fruit.Bananes, 150)
        assertFruitPrice(Fruit.Cerises, 75)
    }

    @Test
    fun `should get the total of the basket`() {
        val basket = Basket()

        basket.add(Fruit.Pommes)
        basket.add(Fruit.Pommes)
        basket.add(Fruit.Cerises)

        assertThat(basket.total()).isEqualTo(275)
    }

    @Test
    fun `a batch of 2 cerises gives a discount of 20`() {
        val basket = Basket()

        basket.add(Fruit.Cerises)
        assertThat(basket.total()).isEqualTo(75)
        basket.add(Fruit.Cerises)
        assertThat(basket.total()).isEqualTo((75 * 2) - 20)
        basket.add(Fruit.Cerises)
        assertThat(basket.total()).isEqualTo((75 * 3) - 20)
        basket.add(Fruit.Cerises)
        assertThat(basket.total()).isEqualTo((75 * 4) - (20 * 2))
    }

    private fun assertFruitPrice(fruit: Fruit, price: Price) {
        val basket = Basket()
        basket.add(fruit)
        assertThat(basket.total()).isEqualTo(price)
    }

}
