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

        mainLoop(basket, sequenceOf("Fruits A", "Fruits B"), print)

        assertAddedFruits(listOf("Fruits A", "Fruits B"))
        assertPrintedText(listOf("100", "100"))
    }

    private fun everyBasketTotalWillBe(total: Int) {
        every { basket.total() }.returns(total)
    }

    private fun assertAddedFruits(fruits: List<String>) {
        fruits.forEach {
            verify { basket.add(it) }
        }
    }

    private fun assertPrintedText(printedLines: List<String>) {
        assertThat(printedText).isEqualTo(printedLines)
    }

}