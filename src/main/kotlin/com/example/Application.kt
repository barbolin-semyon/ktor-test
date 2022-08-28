package com.example

import com.example.features.login.configureLoginRouting
import com.example.features.register.configureRegisterRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {

    Database.connect("jdbc:postgresql://localhost:5432/test", driver = "org.postgresql.Driver",
    password = "res123", user = "postgres")

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureLoginRouting()
        configureRegisterRouting()
        configureRouting()
    }.start(wait = true)
}
