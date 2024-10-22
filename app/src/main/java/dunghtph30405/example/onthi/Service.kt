package dunghtph30405.example.onthi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Api{

    @GET("/Products")
    suspend fun getProducts(): List<Product>


    @GET("Products/{id}")
    suspend fun getProductById(@Path("id") productId: String): Product


    @POST("/Products")
    suspend fun addProduct(@Body product: Product): Product


    @PUT("/Products/{id}")
    suspend fun updateProduct(@Path("id") id: String, @Body product: Product): Product


    @DELETE("/Products/{id}")
    suspend fun deleteProuct(@Path("id") id: String)
}

object RetrofitInstance {
    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl("https://60af714e5b8c300017decbb5.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

}