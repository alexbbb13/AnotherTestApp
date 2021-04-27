package cvdevelopers.takehome.room

import androidx.room.Database
import androidx.room.RoomDatabase
import cvdevelopers.takehome.models.db.ClientDao
import cvdevelopers.takehome.models.db.ClientDb


@Database(entities = [
    ClientDb::class
], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun getClientDao(): ClientDao
}