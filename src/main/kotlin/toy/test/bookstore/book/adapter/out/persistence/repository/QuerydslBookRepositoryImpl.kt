package toy.test.bookstore.book.adapter.out.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import toy.test.bookstore.book.adapter.out.persistence.entity.BookJpaEntity
import toy.test.bookstore.book.adapter.out.persistence.entity.QBookJpaEntity.bookJpaEntity
import toy.test.bookstore.book.application.port.`in`.command.SearchFilterCommand
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils

class QuerydslBookRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : QuerydslBookRepository {

    override fun findAllByCondition(
        command: SearchFilterCommand,
        pageable: Pageable
    ): Page<BookJpaEntity> {

        val predicates = listOfNotNull(
            command.title?.let { bookJpaEntity.title.containsIgnoreCase(it.toString()) },
            command.author?.let { bookJpaEntity.author.containsIgnoreCase(it.toString()) }
        )

        val result = queryFactory
            .selectFrom(bookJpaEntity)
            .where(*predicates.toTypedArray())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(bookJpaEntity.title.asc(), bookJpaEntity.author.asc())
            .fetch()

        val countQuery = queryFactory
            .select(bookJpaEntity.count())
            .from(bookJpaEntity)
            .where(*predicates.toTypedArray())

        return PageableExecutionUtils.getPage(result, pageable) {
            countQuery.fetchOne() ?: 0L
        }
    }
}