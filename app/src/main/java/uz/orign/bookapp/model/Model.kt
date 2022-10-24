package uz.orign.bookapp.model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import uz.orign.bookapp.model.classes.books.Book
import uz.orign.bookapp.model.viewmodel.MyBookViewModel
import uz.orign.bookapp.model.viewmodel.MyStatus
import uz.orign.bookapp.presenter.Contacts

class Model(viewModelStoreOwner: ViewModelStoreOwner, private var lifecycleOwner: LifecycleOwner) :
    Contacts.Model {
    private val myBookViewModel: MyBookViewModel =
        ViewModelProvider(viewModelStoreOwner)[MyBookViewModel::class.java]
    private var list = ArrayList<Book>()

    override fun updateUiFirst(apiGetBooks: Contacts.Model.ApiGetBooks) {
        myBookViewModel.getMyBook().observe(lifecycleOwner) {
            when(it.status){
                MyStatus.SUCCESS->{
                    list.clear()
                    list.addAll(it.data?.results!!.lists[0].books)
                }
                else -> {}
            }
            apiGetBooks.getTrendingBooks(it)
        }
        myBookViewModel.getCategoryNames().observe(lifecycleOwner) {
            apiGetBooks.getCategoriesName(it)
        }
    }

    override fun setCategoryBooks(apiGetBooks: Contacts.Model.ApiGetBooks, title: String) {
        myBookViewModel.getBookByCategoryList(title).observe(lifecycleOwner){
            apiGetBooks.getCategoriesBooks(it)
        }
    }

    override fun setSearch(apiGetBooks: Contacts.Model.ApiGetBooks, title: String) {
        val l = ArrayList<Book>()
        try {
            list.forEach {
                if (it.title.lowercase().substring(0, title.length) == title.lowercase()) {
                    l.add(it)
                }
            }
        }catch (e:Exception){
            l.clear()
        }
        apiGetBooks.getSerach(l)
    }
}