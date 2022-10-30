package com.example.walmartapp

class User(firstname: String, lastname: String, username: String, password: String) {
    var firstname = ""
    var lastname = ""
    var username = ""
    var password = ""

    init {
        this.firstname = firstname
        this.lastname = lastname
        this.username = username
        this.password = password
    }

    override fun toString(): String {
        return "First name: $firstname, Last name: $lastname, User name: $username"
    }
}