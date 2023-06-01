package id.ac.unpas.acilcars.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.acilcars.model.ShowRoom
import id.ac.unpas.acilcars.persistences.AppDatabase
import kotlin.properties.ReadOnlyProperty

@Composable
fun PengelolaanShowRoom() {

    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "pengelolaan-ShowRoom"
    ).build()
    val ShowRoomDao = db.ShowRoomDao()

    val list : LiveData<List<ShowRoom>> = ShowRoomDao.loadAll()
    val items: List<ShowRoom> by list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        FormShowRoom { item ->
            val newList = ArrayList(list)
            newList.add(item)
            list.value = newList
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->

                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal", fontSize = 14.sp)
                        Text(
                            text = item.tanggal, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama", fontSize = 14.sp)
                        Text(
                            text = item.nama, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "No Telp", fontSize = 14.sp)
                        Text(
                            text = item.telp, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}
