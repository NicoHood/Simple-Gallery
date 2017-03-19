package com.simplemobiletools.gallery.models

import com.simplemobiletools.commons.helpers.SORT_BY_DATE_MODIFIED
import com.simplemobiletools.commons.helpers.SORT_BY_NAME
import com.simplemobiletools.commons.helpers.SORT_BY_SIZE
import com.simplemobiletools.commons.helpers.SORT_DESCENDING
import java.io.Serializable

data class Directory(val path: String, val thumbnail: String, val name: String, var mediaCnt: Int, val date_modified: Long, val date_taken: Long,
                     var size: Long) : Serializable, Comparable<Directory> {
    companion object {
        private val serialVersionUID = -6553649863575455L
        var sorting: Int = 0
    }

    fun addSize(bytes: Long) {
        size += bytes
    }

    override fun compareTo(other: Directory): Int {
        var result: Int
        if (sorting and SORT_BY_NAME != 0) {
            result = name.toLowerCase().compareTo(other.name.toLowerCase())
        } else if (sorting and SORT_BY_SIZE != 0) {
            result = if (size == other.size)
                0
            else if (size > other.size)
                1
            else
                -1
        } else if (sorting and SORT_BY_DATE_MODIFIED != 0) {
            result = if (date_modified == other.date_modified)
                0
            else if (date_modified > other.date_modified)
                1
            else
                -1
        } else {
            result = if (date_taken == other.date_taken)
                0
            else if (date_taken > other.date_taken)
                1
            else
                -1
        }

        if (sorting and SORT_DESCENDING != 0) {
            result *= -1
        }
        return result
    }
}
