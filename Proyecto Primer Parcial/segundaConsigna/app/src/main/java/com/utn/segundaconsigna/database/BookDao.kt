package com.utn.segundaconsigna.database

import androidx.room.*
import com.utn.segundaconsigna.entities.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM books ORDER BY id")
    fun fetchAllBooks(): MutableList<Book?>?

    @Query("SELECT * FROM books WHERE id = :id")
    fun fetchBookById(id: Int): Book?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)
}