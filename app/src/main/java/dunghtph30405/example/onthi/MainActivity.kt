package dunghtph30405.example.onthi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
                }
            }
        }
@Composable
fun ShowScreen(viewModel: ApiViewModel = viewModel()) {
    // Gọi fetchUsers() khi màn hình được hiển thị lần đầu
    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }
    val product = viewModel.products
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    Column(Modifier.padding(10.dp, 30.dp, 10.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Danh sách sản phẩm".uppercase(), style = MaterialTheme.typography.titleLarge)
        HorizontalDivider(modifier = Modifier.padding(10.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Đặt số cột là 2
            modifier = Modifier.fillMaxWidth().background(color = Color.Yellow)
                .weight(10f),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp), // Khoảng cách giữa các hàng
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Khoảng cách giữa các cột
        ) {
            items(product) { product ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Hiển thị ảnh bằng AsyncImage
                    AsyncImage(
                        model = product.product_image,
                        contentDescription = "User Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(64.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        error = painterResource(id = R.drawable.ic_launcher_background), // Hình ảnh hiển thị khi có lỗi
                        placeholder = painterResource(id = R.drawable.ic_launcher_background) // Hình ảnh hiển thị trong khi tải
                    )


                    // Hiển thị thông tin sản phẩm
                    Text(text = "${product.product_name}",
                        modifier = Modifier.clickable { selectedProduct = product },
                        fontWeight = FontWeight(weight = 500)
                    )
                    Text(text = "${product.product_price}VND")
                }
            }
        }
        selectedProduct?.let { product ->
            ProductInfoDialog(product = product, onDissmiss = { selectedProduct = null})
        }
    }
}

@Composable
fun ProductInfoDialog(product: Product, onDissmiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDissmiss,
        title = { Text("Thông tin sản phẩm") },
        text = {
            Column {
                Text("Tên sản phẩm: ${product.product_name}")
                Text("Giá: ${product.product_price} VND")
                AsyncImage(
                    model = product.product_image,
                    contentDescription = "Product Image",
                    modifier = Modifier.size(150.dp)
                )
            }
        },
        confirmButton = {
            Button(onClick = onDissmiss) {
                Text("Đóng")
            }
        }
    )
}

