package com.example.features.login

import com.example.cache.InMemoryCache
import com.example.cache.TokenCache
import com.example.database.tokens.TokenDTO
import com.example.database.tokens.Tokens
import com.example.database.users.UserDTO
import com.example.database.users.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive(LoginReceiveModel::class)

        val userDTO = Users.fetchUser(login = receive.login)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "Not found user")
        } else {
            if (userDTO.password == receive.password) {
                val token = UUID.randomUUID().toString()

                Tokens.insert(
                    TokenDTO(
                        rowId = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = token
                    )
                )

                call.respond(LoginResponceRemote(token = token))

            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }

}