package dunghtph30405.example.onthi

class Repository {
    private val api = RetrofitInstance.api


    suspend fun getProducts(): List<Product> {
        return api.getProducts()
    }

    suspend fun getOneProduct(id: String): Product {
        return api.getProductById(id)
    }


    suspend fun addProduct(product: Product): Product {
        return api.addProduct(product)
    }
}