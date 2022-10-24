package uz.orign.bookapp.model.retrofit2

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.orign.bookapp.model.classes.books.MyBook
import uz.orign.bookapp.model.classes.CategoryClass.MyCategory
import uz.orign.bookapp.model.classes.byCategory.BookListByCategory
import uz.orign.bookapp.model.retrofit2.ApiBuilder.API_KEY

interface ApiService {

    @GET("lists/full-overview.json")
    suspend fun getAllBook(@Query("api-key") api_key: String = API_KEY): MyBook

    @GET("lists/names")
    suspend fun getCategoryNameList(@Query("api-key") api_key:String = API_KEY): MyCategory

    @GET("lists/current/{path}.json")
    suspend fun getBookListByCategory(@Path("path") path: String, @Query("api-key") api_key: String = API_KEY): BookListByCategory

}