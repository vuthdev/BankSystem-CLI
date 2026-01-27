package Repository

import DB.Database
import Model.User
import java.util.UUID

class UserRepository {
    fun save(user: User) {
        val sql = "INSERT INTO users(id, username, email, password) VALUES (?, ?, ?, ?)"
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { statement ->
                statement.setObject(1, user.id)
                statement.setString(2, user.username)
                statement.setString(3, user.email)
                statement.setString(4, user.password)
                statement.executeUpdate()
            }
        }
    }

    fun findById(id: UUID?): User? {
        val sql = "SELECT * FROM users WHERE id = ?"
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { statement ->
                statement.setString(1, id.toString())
                val resultSet = statement.executeQuery()
                return if (resultSet.next()) {
                    User(
                        id = UUID.fromString(resultSet.getString("id")),
                        username = resultSet.getString("username"),
                        email = resultSet.getString("email"),
                        password = resultSet.getString("password")
                    )
                } else null
            }
        }
    }

    fun findAll(): List<User> {
        val users = mutableListOf<User>()
        val sql = "SELECT * FROM users"
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { statement ->
                val resultSet = statement.executeQuery()
                while (resultSet.next()) {
                    users.add(User(
                        id = UUID.fromString(resultSet.getString("id")),
                        username = resultSet.getString("username"),
                        email = resultSet.getString("email"),
                        password = resultSet.getString("password")
                    ))
                }
            }
        }
        return users
    }

    fun findByUsername(username: String): User? {
        val sql = "SELECT * FROM users WHERE username = ?"
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { statement ->
                statement.setString(1, username)
                val resultSet = statement.executeQuery()
                return if (resultSet.next()) {
                    User(
                        id = UUID.fromString(resultSet.getString("id")),
                        username = resultSet.getString("username"),
                        email = resultSet.getString("email"),
                        password = resultSet.getString("password")
                    )
                } else null
            }
        }
    }

    fun findByEmail(email: String): User? {
        val sql = "SELECT * FROM users WHERE email = ?"

        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { statement ->
                statement.setString(1, email)
                val resultSet = statement.executeQuery()
                return if (resultSet.next()) {
                    User(
                        id = UUID.fromString(resultSet.getString("id")),
                        username = resultSet.getString("username"),
                        email = resultSet.getString("email"),
                        password = resultSet.getString("password")
                    )
                } else null
            }
        }
    }

    fun deleteById(id: UUID) {
        val sql = "DELETE FROM users WHERE id = ?"
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { statement ->
                statement.setObject(1, id)
                statement.executeUpdate()
            }
        }
    }

    fun deleteByUsername(username: String) {
        val sql = "DELETE FROM users WHERE username = ?"
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { statement ->
                statement.setString(1, username)
                statement.executeUpdate()
            }
        }
    }
}