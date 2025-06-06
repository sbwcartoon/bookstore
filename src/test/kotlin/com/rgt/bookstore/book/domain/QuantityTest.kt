package com.rgt.bookstore.book.domain

import com.rgt.bookstore.book.domain.exception.NegativeValueException
import com.rgt.bookstore.book.domain.vo.Quantity
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

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