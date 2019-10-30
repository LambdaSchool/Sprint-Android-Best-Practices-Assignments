package com.lambdaschool.readinglist

import java.util.*

object SharedPrefsDao {
    private const val ID_LIST_KEY = "id list"
    private const val BOOK_ITEM_KEY_PREFIX = "book#"
    private const val NEXT_ID_KEY = "next id"

    private val allBookIds: String?
        get() = MainActivity.preferences!!.getString(ID_LIST_KEY, "")

    val nextId: String?
        get() = MainActivity.preferences!!.getString(NEXT_ID_KEY, "0")

    fun getBookCsv(id: String): String? {
        return MainActivity.preferences!!.getString(BOOK_ITEM_KEY_PREFIX + id, "")
    }

    fun updateBook(book: Book) {
        val editor = MainActivity.preferences!!.edit()
        val idsString = allBookIds
        val parsedIds = idsString!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val idList = ArrayList<String>(parsedIds.size)
        idList.addAll(Arrays.asList(*parsedIds))
        // new entry
        if (!idList.contains(book.id)) {
            var nextId = Integer.parseInt(MainActivity.preferences!!.getString(NEXT_ID_KEY, "0")!!)
            book.id = nextId.toString()
            editor.putString(NEXT_ID_KEY, Integer.toString(++nextId))
            idList.add(book.id!!)
            val ids = StringBuilder()
            for (id in idList) {
                ids.append(id).append(",")
            }
            editor.putString(ID_LIST_KEY, ids.toString())
            editor.putString(BOOK_ITEM_KEY_PREFIX + book.id!!, book.toCsvString())
            editor.apply()
        } else {
            editor.putString(BOOK_ITEM_KEY_PREFIX + book.id!!, book.toCsvString())
            editor.apply()
        }
    }
}



