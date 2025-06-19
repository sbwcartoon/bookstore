package toy.test.bookstore.book.unit.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import toy.test.bookstore.book.unit.domain.exception.NegativeValueException
import toy.test.bookstore.book.unit.domain.vo.Quantity

class QuantityTest : BehaviorSpec({

    Given("수량을 생성할 때") {
        When("값이 음수일 경우") {
            Then("오류가 발생한다") {
                shouldThrow<NegativeValueException> {
                    Quantity(-1)
                }
            }
        }
    }
})