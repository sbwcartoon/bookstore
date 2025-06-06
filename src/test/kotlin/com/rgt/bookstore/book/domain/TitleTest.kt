package com.rgt.bookstore.book.domain

import com.rgt.bookstore.book.domain.exception.BlankValueException
import com.rgt.bookstore.book.domain.vo.Title
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class TitleTest : BehaviorSpec({

    Given("제목을 생성할 때") {
        When("내용이 없는 경우") {
            Then("오류가 발생한다") {
                shouldThrow<BlankValueException> {
                    Title("")
                }
            }
        }

        When("내용이 공백 제거 후 없는 경우") {
            Then("오류가 발생한다") {
                shouldThrow<BlankValueException> {
                    Title(" ")
                }
            }
        }
    }
})