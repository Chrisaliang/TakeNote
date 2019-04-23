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
        }
    }
}
