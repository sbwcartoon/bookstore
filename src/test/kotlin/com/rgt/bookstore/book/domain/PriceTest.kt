package com.rgt.bookstore.book.domain

import com.rgt.bookstore.book.domain.exception.NegativeValueException
import com.rgt.bookstore.book.domain.vo.Price
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class PriceTest : BehaviorSpec({

    Given("가격을 생성할 때") {
        When("값이 음수일 경우") {
            Then("오류가 발생한다") {
                shouldThrow<NegativeValueException> {
                    Price(-1)
                }
            }
        }
    }
})