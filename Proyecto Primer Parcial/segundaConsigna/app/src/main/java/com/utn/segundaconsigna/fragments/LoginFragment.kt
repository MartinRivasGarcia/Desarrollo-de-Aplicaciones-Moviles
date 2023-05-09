package com.utn.segundaconsigna.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.window.OnBackInvokedDispatcher
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.utn.segundaconsigna.R
import com.utn.segundaconsigna.database.AppDatabase
import com.utn.segundaconsigna.database.UserDao
import com.utn.segundaconsigna.entities.User

class LoginFragment : Fragment() {

    private var users : MutableList<User> = mutableListOf()

    lateinit var mailInput: EditText
    lateinit var passInput: EditText
    lateinit var button : Button
    lateinit var buttonToRegister: Button

    private var db :AppDatabase? = null
    private var userDao: UserDao? = null

    lateinit var v :View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v =  inflater.inflate(R.layout.fragment_login, container, false)

        mailInput = v.findViewById(R.id.inputMail)
        passInput = v.findViewById(R.id.inputPass)
        button    = v.findViewById(R.id.btnLogin)
        buttonToRegister = v.findViewById(R.id.buttonRegister)

        return v
    }

    override fun onStart() {
        super.onStart()
        val sharedPref = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        db = AppDatabase.getInstance(v.context)
        userDao = db?.userDao()

        users = userDao?.fetchAllUsers() as MutableList<User>

        button.setOnClickListener{ //Con el listener atiendo cdo alguien toca el boton
            var login = false
            var mailInputText = mailInput.text.toString()
            var passInputText = passInput.text.toString()

            if (mailInputText.isEmpty()){
                Snackbar.make(v, "Ingrese el mail",Snackbar.LENGTH_SHORT).show()
            }
            else if (passInputText.isEmpty()){
                Snackbar.make(v, "Ingrese la contraseña",Snackbar.LENGTH_SHORT).show()
            }
            else{
                users.forEach{element ->
                    Snackbar.make(v, "Entre",Snackbar.LENGTH_SHORT).show()
                    if (mailInputText == element.email){
                        if (passInputText == element.password){
                            //val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(element.name)
                            //findNavController().navigate(action)
                            editor.putInt("user", element.id)
                            editor.apply()
                            Snackbar.make(v, "Datos correctos",Snackbar.LENGTH_SHORT).show()
                            val navController = Navigation.findNavController(requireView())
                            navController.navigate(R.id.action_loginFragment_to_mainActivity2)
                            login = true
                        }
                    }
                    if (!login){
                        Snackbar.make(v, "Datos incorrectos",Snackbar.LENGTH_SHORT).show()
                    }
                }
            }

        }

        buttonToRegister.setOnClickListener{
            var action = LoginFragmentDirections.actionLoginFragmentToRegister()
            findNavController().navigate(action)
        }

    }




}