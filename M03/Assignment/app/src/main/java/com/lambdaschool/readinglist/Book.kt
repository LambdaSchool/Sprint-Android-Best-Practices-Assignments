package com.lambdaschool.readinglist

class Book {
    var id: String? = null
    var title: String? = null
    var reasonToRead: String? = null
    var isHasBeenRead: Boolean = false


    constructor(id: String, title: String, reasonToRead: String, hasBeenRead: Boolean) {
        this.id = id
        this.title = title
        this.reasonToRead = reasonToRead
        this.isHasBeenRead = hasBeenRead
    }

    constructor(csvString: String) {
        val bookValues = csvString.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (bookValues.size == 4) {
            this.id = bookValues[0]
            this.title = bookValues[1].replace("~@", ",")
            this.reasonToRead = bookValues[2].replace("~@", ",")
            this.isHasBeenRead = bookValues[3] == "true"
        }
    }

    fun toCsvString(): String {
        return String.format("%s,%s,%s,%b",
                id,
                title!!.replace(",", "~@"),
                reasonToRead!!.replace(",", "~@"),
                isHasBeenRead)
    }
}
