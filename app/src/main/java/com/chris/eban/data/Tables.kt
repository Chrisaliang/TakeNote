package com.chris.eban.data

interface Tables {

    /**
     * 事件表
     */
    interface EVENT {
        companion object {

            const val TABLE_NAME = "table_event"

            const val COLUMN_TITLE = "title"

            const val COLUMN_CONTENT = "content"

            const val COLUMN_UPDATE_TIME = "update_time"

            const val COLUMN_CREATE_TIME = "create_time"

        }
    }
}
