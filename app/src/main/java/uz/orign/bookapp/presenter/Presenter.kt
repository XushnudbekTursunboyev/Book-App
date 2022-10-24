package uz.orign.bookapp.presenter

import uz.orign.bookapp.model.classes.CategoryClass.MyCategory
import uz.orign.bookapp.model.classes.byCategory.BookListByCategory
import uz.orign.bookapp.model.classes.books.Book
import uz.orign.bookapp.model.classes.books.MyBook
import uz.orign.bookapp.model.viewmodel.MyResource

class Presenter(
    private val mainView: Contacts.View?,
    private val model: Contacts.Model
) : Contacts.Presenter, Contacts.Model.ApiGetBooks {
    override fun getTrendingBooks(res: MyResource<MyBook>) {
        mainView?.showTrendingBooks(res)
    }

    override fun getCategoriesName(res: MyResource<MyCategory>) {
        mainView?.showCategoriesName(res)
    }

    override fun getCategoriesBooks(res: MyResource<BookListByCategory>) {
        mainView?.showCategoriesBooks(res)
    }

    override fun getSerach(res: ArrayList<Book>) {
        mainView?.showSearch(res)
    }

    override fun onCreateStart() {
        model.updateUiFirst(this)
    }

    override fun setCategory(title: String) {
        model.setCategoryBooks(this, title)
    }

    override fun setSearch(text: String) {
        model.setSearch(this, text)
    }

}