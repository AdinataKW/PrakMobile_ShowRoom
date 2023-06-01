package id.ac.unpas.acilcars.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShowRoom(
    @PrimaryKey val id: String,
    val tanggal: String,
    val nama: String,
    val telp: String
)
