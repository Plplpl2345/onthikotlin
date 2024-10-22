package dunghtph30405.example.onthi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun FirstScreen(navController: NavController) {
    val correctText = "password" // Văn bản đã được định nghĩa sẵn
    var inputText by remember { mutableStateOf("") }
    var showSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.img), // Thay logo của bạn ở đây
            contentDescription = "App Logo",
            modifier = Modifier.size(128.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Ô nhập văn bản
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Nhập văn bản") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button
        Button(onClick = {
            if (inputText == correctText) {
                // Chuyển sang màn hình thứ 2 nếu nhập đúng
                navController.navigate("secondScreen")
            } else {
                // Hiển thị Snackbar nếu nhập sai
                showSnackbar = true
            }
        }) {
            Text("Xác nhận")
        }

        // Snackbar hiển thị lỗi
        if (showSnackbar) {
            LaunchedEffect(Unit) {
                snackbarHostState.showSnackbar("Văn bản nhập sai, vui lòng thử lại!")
                showSnackbar = false // Đặt lại biến sau khi hiển thị
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstScreen") {
        composable("firstScreen") { FirstScreen(navController) }
        composable("secondScreen") { ShowScreen() }
    }
}