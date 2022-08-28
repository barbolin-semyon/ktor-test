package com.example.features.register

import com.example.cache.InMemoryCache
import com.example.cache.TokenCache
import com.example.features.login.LoginReceiveModel
import com.example.features.login.LoginResponceRemote
import com.example.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val controller = RegisterController(call)
            controller.registerNewUser()
            return@post
        }
    }
}

