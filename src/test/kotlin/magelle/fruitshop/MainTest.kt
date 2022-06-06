package magelle.fruitshop

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class MainTest {

    private var printedText = mutableListOf<String>()
    private val print: Print = { text: String -> printedText.add(text) }

    private val basket = mockk<Basket>(relaxed = true)

    @Test
    fun `should receive fruits then print total`() {
        everyBasketTotalWillBe(100)

        mainLoop(basket, sequenceOf("Pommes", "Cerises"), print)

        assertAddedFruits(listOf(Fruit.Pommes, Fruit.Cerises))
        assertPrintedText(listOf("100", "100"))
    }

    @Test
    fun `should print an error message on unknown fruits`() {
        mainLoop(basket, sequenceOf("Unknown fruits"), print)

        assertPrintedText(listOf("Error: Unknown fruits \"Unknown fruits\""))
    }

    @Test
    fun `should accept comma separated list of fruits`() {
        mainLoop(basket, sequenceOf("Cerises, Pommes,Bananes"), print)

        assertAddedFruits(listOf(Fruit.Cerises, Fruit.Pommes, Fruit.Bananes))
    }

    @Test
    fun `should translate Apples and Mele to Pommes`() {
        mainLoop(basket, sequenceOf("Apples, Mele,Pommes"), print)

        assertAddedFruits(3, Fruit.Pommes)
    }

    private fun everyBasketTotalWillBe(total: Int) {
        every { basket.total() }.returns(total)
    }

    private fun assertAddedFruits(fruits: List<Fruit>) {
        fruits.forEach {
            verify { basket.add(it) }
        }
    }

    private fun assertAddedFruits(times: Int, fruit: Fruit) {
        verify(exactly = times) { basket.add(fruit) }
    }

    private fun assertPrintedText(printedLines: List<String>) {
        assertThat(printedText).containsAll(printedLines)
    }

}