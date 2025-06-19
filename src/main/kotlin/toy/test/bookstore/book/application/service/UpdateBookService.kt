package toy.test.bookstore.book.application.service

import toy.test.bookstore.book.application.exception.BookNotFoundException
import toy.test.bookstore.book.application.exception.DuplicateBookException
import toy.test.bookstore.book.application.port.`in`.UpdateBookUseCase
import toy.test.bookstore.book.application.port.`in`.command.UpdateBookCommand
import toy.test.bookstore.book.application.port.out.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateBookService(
    private val repository: BookRepository,
) : UpdateBookUseCase {

    @Transactional
    override fun execute(command: UpdateBookCommand) {
        repository.findById(command.id)
            ?: throw BookNotFoundException(command.id)

        val title = command.title
        val author = command.author
        if (repository.existsByTitleAndAuthorAndIdNot(title, author, command.id)) {
            throw DuplicateBookException(title, author)
        }

        repository.save(command.toDomain())
    }
}