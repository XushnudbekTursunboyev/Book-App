package uz.orign.bookapp.model.classes.books

import androidx.room.PrimaryKey
import java.io.Serializable

data class Book(
    @PrimaryKey val
    id: Int,
    val author: String,
    val book_image: String,
    val description: String,
    val price: String,
    val rank: Int,
    val title: String
):Serializable