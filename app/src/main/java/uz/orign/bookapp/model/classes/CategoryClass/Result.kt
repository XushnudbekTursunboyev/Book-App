package uz.orign.bookapp.model.classes.CategoryClass

data class Result(
    val display_name: String,
    val list_name: String,
    val list_name_encoded: String,
    val newest_published_date: String,
    val oldest_published_date: String,
    val updated: String
)