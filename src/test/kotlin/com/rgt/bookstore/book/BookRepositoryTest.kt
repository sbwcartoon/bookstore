package com.rgt.bookstore.book

import com.rgt.bookstore.book.adapter.out.persistence.mapper.BookJpaMapper
import com.rgt.bookstore.book.adapter.out.persistence.repository.BookJpaRepository
import com.rgt.bookstore.book.config.UnitTest
import com.rgt.bookstore.book.testfixture.BookTestFixture
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.util.*

@UnitTest
class BookRepositoryTest(
    private val jpaRepository: BookJpaRepository,
) : BehaviorSpec({

    Given("책이 있을 때") {
        val book = BookTestFixture.generateBook()

        When("저장하면") {
            val saved = jpaRepository.save(BookJpaMapper.toEntity(book))

            then("DB에 저장된다") {
                jpaRepository.findById(saved.id) shouldBe Optional.of(saved)
            }
        }
    }
})