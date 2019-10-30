package com.lambdaschool.readinglist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBookActivity : AppCompatActivity() {
    private var context: Context? = null
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        FirebaseAnalytics.getInstance(this).setCurrentScreen(this, "EditBookActivity","This is edit book activity!")

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1")
        bundle.putString(FirebaseAnalytics.Param.QUANTITY, "3")
        bundle.putString(FirebaseAnalytics.Param.CONTENT, "2")
        FirebaseAnalytics.getInstance(this).logEvent("edit_book_activity_view", bundle)

        context = this
    }

    override fun onResume() {
        super.onResume()

        save_button.setOnClickListener {

            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1")
            bundle.putString(FirebaseAnalytics.Param.QUANTITY, "3")
            bundle.putString(FirebaseAnalytics.Param.CONTENT, "2")
            FirebaseAnalytics.getInstance(this).logEvent("save_button_click", bundle)

            saveBook()


        }
        cancel_button.setOnClickListener { cancel() }

        val intent = intent
        id = intent.getStringExtra(Constants.NEW_BOOK_TAG)
        val bookCsv = intent.getStringExtra(Constants.EDIT_BOOK_TAG)
        if (bookCsv != null) {

            val bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Book")
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1")
            bundle.putString(FirebaseAnalytics.Param.QUANTITY, "3")
            bundle.putString(FirebaseAnalytics.Param.CONTENT, "2")
            FirebaseAnalytics.getInstance(this).logEvent("add_book_event", bundle)

            val book = Book(bookCsv)
            book_name_text.setText(book.title)
            book_reason_text.setText(book.reasonToRead)
            read_switch.isChecked = book.isHasBeenRead
            id = book.id
        }
    }

    private fun saveBook() {

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Book")
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1")
        bundle.putString(FirebaseAnalytics.Param.QUANTITY, "3")
        bundle.putString(FirebaseAnalytics.Param.CONTENT, "2")
        FirebaseAnalytics.getInstance(this).logEvent("save_book_event", bundle)


        val bookName = book_name_text.text.toString()
        if(bookName.isEmpty()){
            Toast.makeText(this, "Books require a title", Toast.LENGTH_SHORT).show()
            return
        }
        val bookReason = book_reason_text.text.toString()
        val hasBeenRead = read_switch.isChecked
        val book = Book(id!!, bookName, bookReason, hasBeenRead)
        val bookCsv = book.toCsvString()
        val intent = Intent()
        intent.putExtra(Constants.EDIT_BOOK_TAG, bookCsv)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun cancel() {
        val intent = Intent()
        setResult(RESULT_CANCELED, intent)
        finish()
    }

    override fun onBackPressed() {
        saveBook()
    }
}
