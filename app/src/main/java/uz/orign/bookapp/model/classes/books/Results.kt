package uz.orign.bookapp.model.classes.books

data class Results(
    val bestsellers_date: String,
    val lists: List<Lists>,
    val next_published_date: String,
    val previous_published_date: String,
    val published_date: String,
    val published_date_description: String
)