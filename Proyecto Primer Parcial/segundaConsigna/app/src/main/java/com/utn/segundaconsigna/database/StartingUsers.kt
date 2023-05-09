package com.utn.segundaconsigna.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.utn.segundaconsigna.R
import com.utn.segundaconsigna.entities.Book
import com.utn.segundaconsigna.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingUsers(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingUsers", "Pre-populating database...")
            fillWithStartingUsers(context)
        }
    }

    /**
     * Pre-populate database with hard-coded users
     */
    private fun fillWithStartingUsers(context: Context) {
        val users = listOf(
            User(0, "Martin", "martin@gmail.com", "123"),
            User(1, "Jane", "jane@gmail.com", "123"),
            User(2, "Matt", "matt@gmail.com", "123"),
            User(3, "Jeff", "jeff@gmail.com", "123")
        )
        val books = listOf(
            Book(0, "Dune", 1965, "Frank Herbert","Penguin Random House", "https://revistamutaciones.com/wp-content/uploads/2021/09/dune-frank-herbert-novela-adaptacion.jpg"),
            Book(1, "Steelheart", 2013, "Brandon Sanderson","Nova", "https://images.cdn1.buscalibre.com/fit-in/360x360/b5/d7/b5d7d284df534b29feb2a03f640f0f6f.jpg"),
            Book(2, "La quinta ola", 2013, "Rick Yancey","RBA Molino", "https://contentv2.tap-commerce.com/cover/large/9788492955282_1.jpg")
        )
        val dao = AppDatabase.getInstance(context)?.userDao()
        val daoB = AppDatabase.getInstance(context)?.bookDao()

        users.forEach {
            dao?.insertUser(it)
        }
        books.forEach{
            daoB?.insertBook(it)
        }
    }

    /**
     * Pre-populate database with users from a Json file
     */
    private fun fillWithStartingUsersFromJson(context: Context) {
        val dao = AppDatabase.getInstance(context)?.userDao()

        try {
            val users = loadJSONArray(context, R.raw.users)
            for (i in 0 until users.length()) {
                val item = users.getJSONObject(i)
                val user = User(
                    id = 0,
                    name = item.getString("name"),
                    email = item.getString("email"),
                    password = item.getString("password")
                )

                dao?.insertUser(user)
            }
        } catch (e: JSONException) {
            Log.e("fillWithStartingNotes", e.toString())
        }
    }

    /**
     * Utility to load a JSON array from the raw folder
     */
    private fun loadJSONArray(context: Context, file: Int): JSONArray {
        val inputStream = context.resources.openRawResource(file)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}
