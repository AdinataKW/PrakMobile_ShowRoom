package id.ac.unpas.tubesdudaksil.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.tubesdudaksil.model.Mobil
import id.ac.unpas.tubesdudaksil.model.SepedaMotor
import id.ac.unpas.tubesdudaksil.model.Promo

@Database(entities = [Mobil::class, SepedaMotor::class, Promo::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun mobilDao(): MobilDao
    abstract fun sepedaMotorDao(): SepedaMotorDao
    abstract fun promoDao(): PromoDao
}
