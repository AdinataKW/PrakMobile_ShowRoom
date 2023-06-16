package id.ac.unpas.showroom.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.showroom.model.SR_Mobil

@Database(entities = [SR_Mobil::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun sr_mobilDao(): SR_MobilDao
}