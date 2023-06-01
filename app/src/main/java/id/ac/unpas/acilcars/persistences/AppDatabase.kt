package id.ac.unpas.acilcars.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.acilcars.model.ShowRoom
@Database(entities = [ShowRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ShowRoomDao(): ShowRoomDao
}
