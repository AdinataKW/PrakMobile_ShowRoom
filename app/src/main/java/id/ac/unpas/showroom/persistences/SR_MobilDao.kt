package id.ac.unpas.showroom.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.showroom.model.SR_Mobil

@Dao
interface SR_MobilDao {
    @Query("SELECT * FROM SR_Mobil ORDER BY model DESC")
    fun loadAll(): LiveData<List<SR_Mobil>>

    @Query("SELECT * FROM SR_Mobil ORDER BY model DESC")
    suspend fun getList(): List<SR_Mobil>

    @Query("SELECT * FROM SR_Mobil WHERE id = :id")
    suspend fun find(id: String): SR_Mobil?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: SR_Mobil)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SR_Mobil>)

    @Delete
    fun delete(item: SR_Mobil)

    @Query("DELETE FROM SR_Mobil WHERE id = :id")
    fun delete(id: String)
}