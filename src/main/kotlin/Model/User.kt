package Model

import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val username : String = "",
    val email : String = "",
    var password: String = "",
)
