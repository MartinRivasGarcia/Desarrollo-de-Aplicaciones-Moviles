package com.utn.segundaconsigna.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.utn.segundaconsigna.R
import com.utn.segundaconsigna.database.AppDatabase
import com.utn.segundaconsigna.database.UserDao
import com.utn.segundaconsigna.entities.User


class Register : Fragment() {
    lateinit var mailInput: EditText
    lateinit var passInput: EditText
    lateinit var nameInput: EditText

    lateinit var button : Button

    lateinit var v :View

    private var db :AppDatabase? = null
    private var userDao: UserDao? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        v =  inflater.inflate(R.layout.fragment_register, container, false)

        passInput = v.findViewById(R.id.inputRegisterPassword)
        mailInput = v.findViewById(R.id.inputRegisterMail)
        nameInput = v.findViewById(R.id.inputRegisterName)
        button    = v.findViewById(R.id.button_register)



        return v
    }


    override fun onStart() {
        super.onStart()

        val sharedPref = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        db = AppDatabase.getInstance(v.context)
        userDao = db?.userDao()

        button.setOnClickListener{
            var mailInputText = mailInput.text.toString()
            var passInputText = passInput.text.toString()
            var nameInputText = nameInput.text.toString()

            if (mailInputText.isEmpty()){
                Snackbar.make(v, "Ingrese el mail", Snackbar.LENGTH_SHORT).show()
            }
            else if (passInputText.isEmpty()){
                Snackbar.make(v, "Ingrese la contraseña", Snackbar.LENGTH_SHORT).show()
            }
            else if (nameInputText.isEmpty()){
                Snackbar.make(v, "Ingrese el nombre", Snackbar.LENGTH_SHORT).show()
            }
            else{

                userDao?.insertUser(User(0,nameInputText, mailInputText, passInputText))

                var users = userDao?.fetchAllUsers() as MutableList<User>

                users.forEach{element ->
                    if (element.email == mailInputText){
                        editor.putInt("user", element.id)
                        editor.apply()
                    }
                }

                val navController = Navigation.findNavController(requireView())
                navController.navigate(R.id.action_register_to_mainActivity2)
            }
        }
    }


}