package com.utn.segundaconsigna.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
class User(id: Int, name: String, email: String, password: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int

    @ColumnInfo(name = "name")
    var name: String

    @ColumnInfo(name = "email")
    var email: String

    @ColumnInfo(name = "password")
    var password: String

    @ColumnInfo(name = "login")
    var login: Boolean


    init {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
        this.login = false
    }
}


/*
class User(
    var name: String,
    var email: String,
    var password: String
){
    var login:Boolean = false
}*/