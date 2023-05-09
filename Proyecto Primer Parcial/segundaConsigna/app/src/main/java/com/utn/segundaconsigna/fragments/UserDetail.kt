package com.utn.segundaconsigna.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.utn.segundaconsigna.R
import com.utn.segundaconsigna.activities.MainActivity
import com.utn.segundaconsigna.database.AppDatabase
import com.utn.segundaconsigna.database.UserDao
import com.utn.segundaconsigna.entities.User


class UserDetail : Fragment() {
    lateinit var mailInput: EditText
    lateinit var passInput: EditText
    lateinit var nameInput: EditText

    lateinit var buttonModifi : Button
    lateinit var buttonExit   : Button

    lateinit var v :View

    private var db : AppDatabase? = null
    private var userDao: UserDao? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_user_detail, container, false)

        passInput = v.findViewById(R.id.editPassword)
        mailInput = v.findViewById(R.id.editMail)
        nameInput = v.findViewById(R.id.editName)

        buttonModifi    = v.findViewById(R.id.buttonEditUser)
        buttonExit      = v.findViewById(R.id.closeSesion)



        return v
    }

    override fun onStart() {
        super.onStart()
        val sharedPref = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        val idUser = sharedPref.getInt("user",-1)

        db = AppDatabase.getInstance(v.context)
        userDao = db?.userDao()

        val user = userDao?.fetchUserById(idUser)

        mailInput.setHint(user?.email ?: "")
        passInput.setHint(user?.password ?: "")
        nameInput.setHint(user?.name ?: "")

        buttonExit.setOnClickListener {
          /*  val navController = Navigation.findNavController(requireView())
            navController.popBackStack(R.id.action_loginFragment_to_mainActivity2, false)*/
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        buttonModifi.setOnClickListener {
            val newUser = User(idUser,user?.name.toString(),user?.email.toString(),user?.password.toString())

            var nameInputText = nameInput.text.toString()
            var mailInputText = mailInput.text.toString()
            var passInputText = passInput.text.toString()

            if (nameInputText != ""){
                newUser.name = nameInputText
            }
            if (mailInputText != ""){
                newUser.email = mailInputText
            }
            if (passInputText != ""){
                newUser.password = passInputText
            }

            userDao?.updateUser(newUser)
            val navController = Navigation.findNavController(requireView())
            navController.popBackStack()
        }
    }
}