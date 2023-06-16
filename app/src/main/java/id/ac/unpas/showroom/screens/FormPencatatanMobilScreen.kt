package id.ac.unpas.showroom.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.showroom.ui.theme.PurpleGrey80
import id.ac.unpas.showroom.ui.theme.Pink80
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPencatatanMobilScreen(navController : NavHostController,
          id: String? = null, modifier: Modifier = Modifier) {

    val viewModel = hiltViewModel<PengelolaanMobilViewModel>()
    val merk = remember { mutableStateOf(TextFieldValue("")) }
    val model = remember { mutableStateOf(TextFieldValue("")) }
    val bahanBakar = remember { mutableStateOf(TextFieldValue("")) }
    val dijual = remember { mutableStateOf(TextFieldValue("")) }
    val deskripsi = remember { mutableStateOf(TextFieldValue("")) }

    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else
        "Simpan"

    val scope = rememberCoroutineScope()
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Tanggal") },
            value = merk.value,
            onValueChange = {
                merk.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "Toyota") }
        )
        OutlinedTextField(
            label = { Text(text = "Model") },
            value = model.value,
            onValueChange = {
                model.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Avanza") }
        )
        OutlinedTextField(
            label = { Text(text = "Bahan Bakar") },
            value = bahanBakar.value,
            onValueChange = {
                bahanBakar.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Bensin") }
        )
        OutlinedTextField(
            label = { Text(text = "Dijual") },
            value = dijual.value,
            onValueChange = {
                dijual.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = " ") }
        )
        OutlinedTextField(
            label = { Text(text = "Deskripsi") },
            value = deskripsi.value,
            onValueChange = {
                deskripsi.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Mobil ini ....") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            containerColor = PurpleGrey80,
            contentColor = Pink80
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            containerColor = PurpleGrey80,
            contentColor = Pink80
        )

        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                if (id == null) {
                    scope.launch {
                        viewModel.insert(merk.value.text, model.value.text,
                            bahanBakar.value.text, dijual.value.text, deskripsi.value.text)
                    }
                } else {
                    scope.launch {
                        viewModel.update(id, merk.value.text, model.value.text,
                            bahanBakar.value.text, dijual.value.text, deskripsi.value.text)
                    }
                }
                navController.navigate("pengelolaan-mobil")
            }, colors = loginButtonColors) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                merk.value = TextFieldValue("")
                model.value = TextFieldValue("")
                bahanBakar.value = TextFieldValue("")
                dijual.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        isLoading.value = it
    }
    if (id != null) {
        LaunchedEffect(scope) {
            viewModel.loadItem(id) { sr_Mobil ->
                sr_Mobil?.let {
                    merk.value =
                        TextFieldValue(sr_Mobil.merk)
                    model.value =
                        TextFieldValue(sr_Mobil.model)
                    bahanBakar.value =
                        TextFieldValue(sr_Mobil.bahanBakar)
                    dijual.value =
                        TextFieldValue(sr_Mobil.dijual)
                    deskripsi.value =
                        TextFieldValue(sr_Mobil.deskripsi)
                }
            }
        }
    }
}



