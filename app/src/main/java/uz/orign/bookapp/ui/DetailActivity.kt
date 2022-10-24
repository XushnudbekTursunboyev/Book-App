package uz.orign.bookapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import uz.orign.bookapp.R
import uz.orign.bookapp.databinding.ActivityDetailBinding
import uz.orign.bookapp.model.classes.books.Book
import uz.orign.bookapp.model.classes.byCategory.BookCt

class DetailActivity : AppCompatActivity() {

    private var isCategory = -1
    private lateinit var book: BookCt

    private lateinit var bn: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bn = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bn.root)
        isCategory = intent.getIntExtra("isCategory", -1)
        book = if (isCategory == 1) {
            intent.getSerializableExtra("keyBook") as BookCt
        } else {
            val book0 = intent?.getSerializableExtra("keyBook") as Book
            BookCt(
                0,
                book_image = book0.book_image,
                title = book0.title,
                author = book0.author,
                rank = book0.rank,
                price = book0.price,
                description = book0.description
            )
        }

        bn.apply {
            Glide.with(this@DetailActivity).load(book.book_image).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(imBook)
            tvBookName.text = book.title
            tvBookAuthor.text = book.author
            tvRatingApi.text = book.rank.toString()
            tvAudioApi.text = book.price
            tvAbout.text = book.description

            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}