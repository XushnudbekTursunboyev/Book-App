package uz.orign.bookapp.model.classes.byCategory


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "book")
data class BookCt(
    @PrimaryKey val id: Int,
    val author: String,
    val book_image: String,
    val description: String,
    val price: String,
    val rank: Int,
    val title: String
) : Serializable