package com.example.test_neversitup.room

import androidx.room.*

@Dao
interface CustomerDao {
    @Delete
    fun deleteCustomerData(res: LoginModel?)

    @Insert
    fun insertCustomerData(res: LoginModel?)

    @Update
    fun updateCustomerData(res: LoginModel?)

    @Query("SELECT * FROM customer")
    fun getCustomerData(): LoginModel?

    @Query("DELETE FROM customer")
    fun deleteTable()
}