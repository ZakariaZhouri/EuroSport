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

@Entity(tableName = "Video")
class VideoEntity : MediaEntity {
    companion object {
        internal const val VIDEO_TABLE = "`Video`"

        internal const val COLUMN_ROW_ID = "id"
        internal const val COLUMN_THUMB = "video_thumb"
        internal const val COLUMN_URL = "video_url"
        internal const val COLUMN_VIEWS = "video_view"
    }


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ROW_ID)
    var rowId: Long = 0L

    @ColumnInfo(name = COLUMN_THUMB)
    var thumb: String? = null

    @ColumnInfo(name = COLUMN_URL)
    var url: String? = null

    @ColumnInfo(name = COLUMN_VIEWS)
    var views: Long? = null

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