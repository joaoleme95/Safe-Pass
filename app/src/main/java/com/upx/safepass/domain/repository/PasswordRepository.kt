package com.upx.safepass.domain

import com.upx.safepass.data.PasswordDao
import com.upx.safepass.data.PasswordEntity

class PasswordRepository(private val dao: PasswordDao) {
    fun getAll() = dao.getAll()
    suspend fun insert(password: PasswordEntity) = dao.insert(password)
}