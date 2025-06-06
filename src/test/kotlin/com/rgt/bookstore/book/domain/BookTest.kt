package com.rgt.bookstore.book.domain

import com.rgt.bookstore.book.testfixture.BookTestFixture
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.util.*

class BookTest : BehaviorSpec({

    Given("id가 서로 다르고, 제목 및 저자 이름이 동일한 책이 있을 때") {
        val book1 = BookTestFixture.generateBook(id = UUID.randomUUID(), title = "title", author = "author")
        val book2 = BookTestFixture.generateBook(id = UUID.randomUUID(), title = "title", author = "author")

        When("서로 비교하면") {
            Then("같다고 판정한다") {
                book1 shouldBe book2
            }
        }
    }
})