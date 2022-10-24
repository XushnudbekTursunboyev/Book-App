package uz.orign.bookapp.model.classes.books

data class MyBook(
    val copyright: String,
    val num_results: Int,
    val results: Results,
    val status: String
)