package dunghtph30405.example.onthi

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ApiViewModel : ViewModel() {
    private val repository = Repository()
    var products = mutableStateListOf<Product>()
        private set

    // Fetch all users (READ)
    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val fetchedProducts = repository.getProducts()
                products.clear()
                products.addAll(fetchedProducts)
//                Log.d("zzzzzzz", "fetchUsers: ket qua lay danh sach " + users.size)
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }


    // Add a new user (CREATE)
    fun addUser(product:Product) {
        viewModelScope.launch {
            try {
                val newproduct = repository.addProduct(product)
                products.add(newproduct)
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }
}