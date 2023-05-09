package com.utn.segundaconsigna.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utn.segundaconsigna.R
import com.utn.segundaconsigna.entities.Book

class BookAdapter(
    var bookList: MutableList <Book>,
    var onClick:(Int) -> Unit //unit es equivalente a void, esto es para hacer el callback

) : RecyclerView.Adapter<BookAdapter.BookHolder>(){
    class BookHolder (v: View, context: Context) : RecyclerView.ViewHolder(v){
        private var view: View
        private var context : Context
        init {
            this.view = v
            this.context = context
        }
        fun setName(name: String){
            var txtName: TextView = view.findViewById(R.id.txtName)
            txtName.text = name
        }
        fun setAuthor(name: String){
            var txtAuthor: TextView = view.findViewById(R.id.txtAuthor)
            txtAuthor.text = name
        }

        fun setImage(name: String){
            var imgBook: ImageView = view.findViewById(R.id.img)
            var url = name
           // url = "https://www.superherodb.com/pictures2/portraits/10/050/15560.jpg"
            Glide
                .with(context)
                .load(url)
                .circleCrop()
                .override(90, 90)
                .into(imgBook)


        }

        fun getCard() :CardView{
            return view.findViewById(R.id.cardBook)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.setName(bookList[position].name.toString())
        holder.setAuthor(bookList[position].author.toString())
        holder.setImage(bookList[position].imageUrl.toString())
        holder.getCard().setOnClickListener{
            //accion al hacer click en el card
            //Quiero hacer un action para cambiar de pagina pero eso lo hago desde el fragment
            //Por lo q hacemos un callback (mandamos la funcion como parametro)
            onClick(position) //En el fragment seguimos
        }
    }
}