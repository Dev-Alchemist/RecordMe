package com.steve.recorder.voicerecorder.data

import javax.inject.Inject

class RecordRepository @Inject constructor(val recordDao: RecordDao) {

    suspend fun insertRecord(record: Record) = recordDao.insertRecords(record)
    suspend fun getRecordByTitle(title: String) = recordDao.getRecord(title)
    suspend fun delete(id: Int) = recordDao.delete(id)
    fun getAllRecords() = recordDao.getAllRecords()
}