package id.ac.unpas.acilcars.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.acilcars.model.ShowRoom

@Dao
interface ShowRoomDao {
    @Query("SELECT * FROM ShowRoom")
    fun loadAll(): LiveData<List<ShowRoom>>
    @Query("SELECT * FROM ShowRoom WHERE id = :id")
    fun find(id: String): ShowRoom?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: ShowRoom)
    @Delete
    fun delete(item: ShowRoom)
}