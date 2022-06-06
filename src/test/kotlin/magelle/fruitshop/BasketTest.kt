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
        assertBasketPrice(listOf(Fruit.Apples), 100)
        assertBasketPrice(listOf(Fruit.Mele), 100)
    }

    @Test
    fun `should get the total of the basket`() {
        assertBasketPrice(listOf(Fruit.Pommes, Fruit.Pommes, Fruit.Cerises), 275)
    }

    @Test
    fun `a batch of 2 cerises gives a discount of 20`() {
        val cerisesDiscount = 20
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

    @Test
    fun `a batch of 3 Apples cost 200`() {
        assertBasketPrice(List(1) { Fruit.Apples }, 100)
        assertBasketPrice(List(3) { Fruit.Apples }, 200)
        assertBasketPrice(List(4) { Fruit.Apples }, 100 + 200 - 100)
        assertBasketPrice(List(6) { Fruit.Apples }, 200 + 200 - 100)
    }

    @Test
    fun `a batch of 2 Mele cost 150`() {
        assertBasketPrice(List(1) { Fruit.Mele }, 100)
        assertBasketPrice(List(2) { Fruit.Mele }, 100)
        assertBasketPrice(List(3) { Fruit.Mele }, 100 + 100)
        assertBasketPrice(List(4) { Fruit.Mele }, 100 + 100 - 100)
    }

    @Test
    fun `a discount of 100 for 4 apples of any translation`() {
        assertBasketPrice(listOf(Fruit.Pommes, Fruit.Pommes, Fruit.Mele, Fruit.Apples), 400 - 100)
    }

    private fun assertBasketPrice(fruits: List<Fruit>, price: Int) {
        val basket = Basket()

        fruits.forEach(basket::add)

        assertThat(basket.total()).isEqualTo(price)
    }

}
