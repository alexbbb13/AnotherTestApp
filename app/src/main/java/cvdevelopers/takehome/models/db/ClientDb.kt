package cvdevelopers.takehome.models.db

import androidx.room.Entity

@Entity(tableName = "clients", primaryKeys = ["id"])
data class ClientDb(
    val id: Int,
    val clientdata: String  //Just as a cache and because it's a quick test
)