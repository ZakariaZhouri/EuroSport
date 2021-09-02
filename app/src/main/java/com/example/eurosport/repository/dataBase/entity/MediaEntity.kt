package com.example.eurosport.repository.dataBase.entity


interface MediaEntity {
    companion object {
        internal const val COLUMN_ROW_NETWORK_ID = "network_id"
        internal const val COLUMN_TITLE = "title"
        internal const val COLUMN_DATE = "date"
        internal const val COLUMN_SPORT_ID = "sport_id"
        internal const val COLUMN_SPORT = "sport"
    }

    var networkId: Long?
    var title: String?
    var date: Float?
    var sportId: Long?
    var sportName: String?
}