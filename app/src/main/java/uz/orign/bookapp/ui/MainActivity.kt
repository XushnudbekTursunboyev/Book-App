package uz.orign.bookapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import uz.orign.bookapp.databinding.ActivityMainBinding
import uz.orign.bookapp.model.Model
import uz.orign.bookapp.model.classes.CategoryClass.MyCategory
import uz.orign.bookapp.model.classes.books.Book
import uz.orign.bookapp.model.classes.books.MyBook
import uz.orign.bookapp.model.classes.byCategory.BookListByCategory
import uz.orign.bookapp.model.viewmodel.MyResource
import uz.orign.bookapp.model.viewmodel.MyStatus
import uz.orign.bookapp.presenter.Contacts
import uz.orign.bookapp.presenter.Presenter
import uz.orign.bookapp.adapters.BooksbyCategoryAdapter
import uz.orign.bookapp.adapters.CategoryAdapter
import uz.orign.bookapp.adapters.TrendingBooksAdapter

class MainActivity : AppCompatActivity(), Contacts.View {
    private var presenter: Presenter? = null
    private lateinit var bn: ActivityMainBinding

    private var categoryAdapter = CategoryAdapter {
        presenter?.setCategory(it.list_name)
    }
    private var booksByCategoryAdapter = BooksbyCategoryAdapter {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("isCategory", 1)
        intent.putExtra("keyBook", it)
        startActivity(intent)
    }
    private var trendingBooksAdapter = TrendingBooksAdapter {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("isCategory", 0)
        intent.putExtra("keyBook", it)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bn = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bn.root)

        presenter = Presenter(this, Model(this, this))
        presenter?.onCreateStart()

        bn.apply {
            searchView.addTextChangedListener {
                if (it.toString() == "") {
                    rvCategories.visibility = View.VISIBLE
                    tvCategories.visibility = View.VISIBLE
                    rvAuthors.visibility = View.VISIBLE
                    tvAuthors.visibility = View.VISIBLE
                    tvTopBooks.visibility = View.VISIBLE
                    presenter?.onCreateStart()
                } else {
                    rvCategories.visibility = View.GONE
                    rvAuthors.visibility = View.GONE
                    tvCategories.visibility = View.GONE
                    tvAuthors.visibility = View.GONE
                    tvTopBooks.visibility = View.GONE
                    presenter?.setSearch(it.toString())
                }
            }
        }

    }

    override fun showTrendingBooks(res: MyResource<MyBook>) {
        when (res.status) {
            MyStatus.SUCCESS -> {
                val l = res.data?.results!!.lists[0].books
                trendingBooksAdapter.submitList(l)
                bn.rvBooks.adapter = trendingBooksAdapter
                bn.progressTrendingBooks.visibility = View.GONE
            }
            MyStatus.ERROR -> {
                bn.progressTrendingBooks.visibility = View.GONE
                Toast.makeText(this, "Error ", Toast.LENGTH_SHORT).show()
            }
            MyStatus.LOADING -> {
                bn.progressTrendingBooks.visibility = View.VISIBLE
            }
        }
    }

    override fun showSearch(res: ArrayList<Book>) {
        trendingBooksAdapter.submitList(res)
        bn.rvBooks.adapter = trendingBooksAdapter
    }

    override fun showCategoriesName(res: MyResource<MyCategory>) {
        when (res.status) {
            MyStatus.SUCCESS -> {
                categoryAdapter.submitList(res.data?.results!!)
                bn.rvCategories.adapter = categoryAdapter
                presenter?.setCategory(res.data.results[0].list_name)
                bn.progressCategoryNames.visibility = View.GONE
            }
            MyStatus.ERROR -> {
                bn.progressCategoryNames.visibility = View.GONE
                Toast.makeText(this, "Error. Check your internet connection!", Toast.LENGTH_SHORT).show()
            }
            MyStatus.LOADING -> {
                bn.progressCategoryNames.visibility = View.VISIBLE
            }
        }
    }

    override fun showCategoriesBooks(res: MyResource<BookListByCategory>) {
        when (res.status) {
            MyStatus.SUCCESS -> {
                val l = res.data?.results?.books!!
                booksByCategoryAdapter.submitList(l)
                bn.rvAuthors.adapter = booksByCategoryAdapter
                bn.progressAuthor.visibility = View.GONE

            }
            MyStatus.ERROR -> {
                bn.progressAuthor.visibility = View.GONE

                Toast.makeText(this, "Error category books", Toast.LENGTH_SHORT).show()
            }
            MyStatus.LOADING -> {
                bn.progressAuthor.visibility = View.VISIBLE

            }
        }
    }


}