package cvdevelopers.takehome.models.db

import androidx.room.*
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface ClientDao {

    @Query("SELECT * FROM clients")
    fun getClients(): List<ClientDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClients(items: List<ClientDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOneClient(item: ClientDb): Single<Long>

    @Transaction
    fun replaceClients(items: List<ClientDb>) {
        insertClients(items)
    }

    @Query("SELECT * FROM clients WHERE id >= :from AND id <= :to")
    fun getTags(from: Int, to: Int): Maybe<List<ClientDb>>

    @Query("DELETE FROM clients")
    fun deleteAll()

    @Delete
    fun removeById(tags: List<ClientDb>): Single<Int>
}