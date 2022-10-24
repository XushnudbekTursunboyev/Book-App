package uz.orign.bookapp.model.classes.CategoryClass

data class MyCategory(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)