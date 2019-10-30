package com.lambdaschool.readinglist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

object BooksController {

    private fun buildItemView(book: Book, context: Context): TextView {
        val bookView = TextView(context)
        bookView.text = book.title
        bookView.textSize = 18f
        if (book.isHasBeenRead) bookView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
        bookView.setOnClickListener {
            val intent = Intent(context, EditBookActivity::class.java)
            intent.putExtra(Constants.EDIT_BOOK_TAG, book.toCsvString())
            (context as Activity).startActivityForResult(intent, Constants.EDIT_BOOK_REQUEST_CODE)
        }
        return bookView
    }

    fun getBooksView(context: Context): LinearLayout {
        val books = BookRepo.bookArray
        val booksView = LinearLayout(context)
        booksView.orientation = LinearLayout.VERTICAL
        for (book in books) {
            booksView.addView(buildItemView(book!!, context))
        }
        return booksView
    }

    fun handleEditActivityResult(intent: Intent) {
        val bookCsv = intent.getStringExtra(Constants.EDIT_BOOK_TAG)
        val book = Book(bookCsv)
        BookRepo.updateBook(book)
    }
}
