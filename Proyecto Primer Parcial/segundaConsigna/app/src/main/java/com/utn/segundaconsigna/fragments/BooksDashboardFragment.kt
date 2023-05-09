package com.utn.segundaconsigna.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.utn.segundaconsigna.R
import com.utn.segundaconsigna.adapters.BookAdapter
import com.utn.segundaconsigna.database.AppDatabase
import com.utn.segundaconsigna.database.BookDao
import com.utn.segundaconsigna.entities.Book

class BooksDashboardFragment : Fragment() {

    lateinit var v : View

    lateinit var recBook : RecyclerView
    lateinit var adapter: BookAdapter
    lateinit var button : Button

    private var books : MutableList<Book> = mutableListOf()

    private var db : AppDatabase? = null
    private var bookDao: BookDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_books_dashboard, container, false)
        recBook = v.findViewById(R.id.recBook)
        button    = v.findViewById(R.id.addBook)

        return v
    }

    override fun onStart() {
        super.onStart()
        //Aca ejecuto la funcion q se hace al hacer click
        //Tengo q reemplazar el bookRepository.book con una lista de lo que haya en la bdd
        button.setOnClickListener{
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_booksDashboardFragment_to_newBook)
        }

        db = AppDatabase.getInstance(v.context)
        bookDao = db?.bookDao()

        books = bookDao?.fetchAllBooks() as MutableList<Book>

        adapter = BookAdapter(books){position -> //Recibe la posicion
            Snackbar.make(v, "Hice click en ${books[position as Int].name}", Snackbar.LENGTH_SHORT).show()
            //Aca podria hacer un action
            var action = BooksDashboardFragmentDirections.actionBooksDashboardFragmentToBookDetailFragment(books[position])
            findNavController().navigate(action)
        }
        recBook.layoutManager = LinearLayoutManager(context)
        recBook.adapter = adapter
    }

}