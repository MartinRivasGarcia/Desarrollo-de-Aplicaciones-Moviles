package com.utn.segundaconsigna.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.utn.segundaconsigna.R
import com.utn.segundaconsigna.database.AppDatabase
import com.utn.segundaconsigna.database.BookDao
import com.utn.segundaconsigna.entities.Book
import com.utn.segundaconsigna.entities.User


class NewBook : Fragment() {
    lateinit var yearInput: EditText
    lateinit var authorInput: EditText
    lateinit var nameInput: EditText
    lateinit var imageInput: EditText
    lateinit var editorialInput: EditText

    lateinit var button : Button

    lateinit var v :View

    private var db :AppDatabase? = null
    private var bookDao: BookDao? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_new_book, container, false)

        yearInput       = v.findViewById(R.id.yearInputBook)
        authorInput     = v.findViewById(R.id.authorInputBook)
        nameInput       = v.findViewById(R.id.nameInputBook)
        imageInput      = v.findViewById(R.id.imagenInputBook)
        editorialInput  = v.findViewById(R.id.editorialInputBook)

        button    = v.findViewById(R.id.button_add_book)



        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        bookDao = db?.bookDao()

        button.setOnClickListener{
            var yearInputText = yearInput.text.toString()
            var authorInputText = authorInput.text.toString()
            var nameInputText = nameInput.text.toString()
            var imageInputText = imageInput.text.toString()
            var editorialInputText = editorialInput.text.toString()

            if (yearInputText.isEmpty()){
                Snackbar.make(v, "Ingrese el año", Snackbar.LENGTH_SHORT).show()
            }
            else if (authorInputText.isEmpty()){
                Snackbar.make(v, "Ingrese el autor", Snackbar.LENGTH_SHORT).show()
            }
            else if (nameInputText.isEmpty()){
                Snackbar.make(v, "Ingrese el nombre", Snackbar.LENGTH_SHORT).show()
            }
            else if (imageInputText.isEmpty()){
                Snackbar.make(v, "Ingrese la imagen", Snackbar.LENGTH_SHORT).show()
            }
            else if (editorialInputText.isEmpty()){
                Snackbar.make(v, "Ingrese la editorial", Snackbar.LENGTH_SHORT).show()
            }
            else{

                bookDao?.insertBook(Book(0, nameInputText, yearInputText.toInt(),authorInputText, editorialInputText,imageInputText))

                val navController = Navigation.findNavController(requireView())
                navController.popBackStack()
            }
        }
    }

}