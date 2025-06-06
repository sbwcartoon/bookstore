package com.rgt.bookstore.book.adapter.out.persistence.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "book",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["title", "author"])
    ],
)
class BookJpaEntity(
    @Id
    val id: String,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val author: String,

    @Column(nullable = false)
    val price: Int,

    @Column(nullable = false)
    val quantity: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BookJpaEntity) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}