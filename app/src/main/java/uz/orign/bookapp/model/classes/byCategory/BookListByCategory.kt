package uz.orign.bookapp.model.classes.byCategory

data class BookListByCategory(
    val copyright: String,
    val last_modified: String,
    val num_results: Int,
    val results: ResultsCt,
    val status: String
)