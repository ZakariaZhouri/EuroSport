package com.example.eurosport.repository.dataBase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eurosport.repository.SportModel
import com.example.eurosport.repository.dataBase.entity.MediaEntity.Companion.COLUMN_DATE
import com.example.eurosport.repository.dataBase.entity.MediaEntity.Companion.COLUMN_ROW_NETWORK_ID
import com.example.eurosport.repository.dataBase.entity.MediaEntity.Companion.COLUMN_SPORT
import com.example.eurosport.repository.dataBase.entity.MediaEntity.Companion.COLUMN_SPORT_ID
import com.example.eurosport.repository.dataBase.entity.MediaEntity.Companion.COLUMN_TITLE
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "Storie")
class StorieEntity : MediaEntity {

    companion object {
        internal const val STORIE_TABLE = "`Storie`"

        internal const val COLUMN_TEASER = "storie_teaser"
        internal const val COLUMN_IMAGE = "storie_image"
        internal const val COLUMN_AUTHOR = "storie_author"


        internal const val COLUMN_ROW_ID = "id"
    }


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ROW_ID)
    var rowId: Long = 0L

    @ColumnInfo(name = COLUMN_TEASER)
    var teaser: String? = null

    @ColumnInfo(name = COLUMN_IMAGE)
    var image: String? = null

    @ColumnInfo(name = COLUMN_AUTHOR)
    var author: String? = null

    @ColumnInfo(name = COLUMN_ROW_NETWORK_ID)
    override var networkId: Long? = null

    @ColumnInfo(name = COLUMN_TITLE)
    override var title: String? = null

    @ColumnInfo(name = COLUMN_DATE)
    override var date: Float? = null

    @ColumnInfo(name = COLUMN_SPORT_ID)
    override var sportId: Long? = null

    @ColumnInfo(name = COLUMN_SPORT)
    override var sportName: String? = null

}