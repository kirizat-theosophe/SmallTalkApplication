package com.example.uke_3_4_oppgave.database

import androidx.room.*
import com.example.uke_3_4_oppgave.UserObject

@Dao
interface UserDAO {
    @Delete
    fun deleteUser(userToDelete: UserObject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userToInsert: UserObject)

    @Query("SELECT * FROM user_table LIMIT 1")
    fun getUser(): UserObject?
}