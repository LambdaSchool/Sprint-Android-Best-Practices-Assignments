package com.lambdaschool.readinglist

object BookRepo {

    val bookArray: Array<Book?>
        get() {
            val books = arrayOfNulls<Book>(Integer.parseInt(SharedPrefsDao.nextId!!))
            for (i in books.indices) {
                val bookCsv = SharedPrefsDao.getBookCsv(i.toString())
                books[i] = bookCsv?.let { Book(it) }
            }
            return books
        }

    fun getBook(id: Int): Book {
        return Book(SharedPrefsDao.getBookCsv(Integer.toString(id))!!)
    }

    fun nextId(): Int {
        return Integer.parseInt(SharedPrefsDao.nextId)
    }

    fun updateBook(book: Book) {
        SharedPrefsDao.updateBook(book)
    }

}
