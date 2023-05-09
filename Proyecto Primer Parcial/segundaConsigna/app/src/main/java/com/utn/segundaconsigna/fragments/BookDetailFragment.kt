package com.utn.segundaconsigna.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.utn.segundaconsigna.R
import com.utn.segundaconsigna.database.AppDatabase
import com.utn.segundaconsigna.database.BookDao
import com.utn.segundaconsigna.entities.Book

class BookDetailFragment : Fragment() {

    lateinit var v : View
    lateinit var arg : Book
    lateinit var nameTextView : TextView
    lateinit var authorTextView : TextView
    lateinit var image : ImageView
    lateinit var imageEdit: EditText
    lateinit var editorialEdit: EditText

    lateinit var buttonEdit : Button
    lateinit var buttonDelete: Button

    private var db : AppDatabase? = null
    private var bookDao: BookDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_book_detail, container, false)

        arg = BookDetailFragmentArgs.fromBundle(requireArguments()).bookDetail

        nameTextView = v.findViewById(R.id.nameTextView)
        authorTextView = v.findViewById(R.id.authorTextView)
        imageEdit = v.findViewById(R.id.editImageBook)
        editorialEdit = v.findViewById(R.id.editEditorialBook)

        nameTextView.setText(arg.name)
        authorTextView.setText(arg.author)
        imageEdit.setHint(arg.imageUrl)
        editorialEdit.setHint(arg.editorial)

        image = v.findViewById(R.id.imageView)
        //La url de la imagen la puede conseguir de los argumentos que me manda el Bundle
        Glide
            .with(this)
            .load(arg.imageUrl)
            .circleCrop()
            .override(100, 100)
            .into(image)

        buttonEdit = v.findViewById(R.id.editBook)
        buttonDelete = v.findViewById(R.id.deleteBook)

        return v
    }

    override fun onStart() {
        super.onStart()

        arg = BookDetailFragmentArgs.fromBundle(requireArguments()).bookDetail

        db = AppDatabase.getInstance(v.context)
        bookDao = db?.bookDao()

        buttonDelete.setOnClickListener {
            bookDao?.fetchBookById(arg.id)?.let { it1 -> bookDao?.deleteBook(it1) }
            val navController = Navigation.findNavController(requireView())
            navController.popBackStack()
        }

        buttonEdit.setOnClickListener {
            val newBook = Book(arg.id,arg.name , arg.year,arg.author, arg.editorial,arg.imageUrl)

            var imageInputText = imageEdit.text.toString()
            var editorialInputText = editorialEdit.text.toString()

            if (imageInputText != ""){
                newBook.imageUrl = imageInputText
            }
            if (editorialInputText != ""){
                newBook.editorial = editorialInputText
            }

            bookDao?.updateBook(newBook)
            val navController = Navigation.findNavController(requireView())
            navController.popBackStack()
        }
    }

}