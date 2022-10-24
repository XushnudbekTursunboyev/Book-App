package uz.orign.bookapp.model.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import uz.orign.bookapp.model.classes.books.MyBook
import uz.orign.bookapp.model.classes.CategoryClass.MyCategory
import uz.orign.bookapp.model.classes.byCategory.BookListByCategory
import uz.orign.bookapp.model.retrofit2.ApiBuilder
import java.lang.Exception

class MyBookViewModel : ViewModel() {
    private val TAG = "MyBookViewModel"
    private val myBookViewModel = MutableLiveData<MyResource<MyBook>>()
    private val categoryNameList = MutableLiveData<MyResource<MyCategory>>()
    private val getBookListByCategory = MutableLiveData<MyResource<BookListByCategory>>()

    fun getMyBook():LiveData<MyResource<MyBook>>{
        val apiService = ApiBuilder.apiService()

        viewModelScope.launch {
            myBookViewModel.postValue(MyResource.loading(null))
            categoryNameList.postValue(MyResource.loading(null))

            try {
                coroutineScope {
                    val async1 = async { apiService.getAllBook() }
                    val await = async1.await()
                    myBookViewModel.postValue(MyResource.success(await))
                }

            }catch (e:Exception){
                myBookViewModel.postValue(MyResource.error(e.message, null))
            }
        }
        return myBookViewModel
    }

    fun getCategoryNames():LiveData<MyResource<MyCategory>>{
        val apiService = ApiBuilder.apiService()

        viewModelScope.launch {
            categoryNameList.postValue(MyResource.loading(null))
            try {
                coroutineScope {
                    val async2 = async { apiService.getCategoryNameList() }

                    val await2 = async2.await()
                    categoryNameList.postValue(MyResource.success(await2))
                }

            }catch (e:Exception){
                categoryNameList.postValue(MyResource.error(e.message, null))
            }
        }
        return categoryNameList
    }

    fun getBookByCategoryList(title:String):LiveData<MyResource<BookListByCategory>>{
        val apiService = ApiBuilder.apiService()

        viewModelScope.launch {
            getBookListByCategory.postValue(MyResource.loading(null))
            try {
                coroutineScope {
                    val async2 = async { apiService.getBookListByCategory(title) }

                    val await2 = async2.await()
                    getBookListByCategory.postValue(MyResource.success(await2))
                }

            }catch (e:Exception){
                getBookListByCategory.postValue(MyResource.error(e.message, null))
            }
        }
        return getBookListByCategory
    }
}