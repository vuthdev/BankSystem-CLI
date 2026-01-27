package DB

import java.sql.Connection
import java.sql.DriverManager

object Database {
    private const val DB_URL = "jdbc:sqlite:bank.db"

    init {
        getConnection().use { conn ->
            conn.createStatement().use { statement ->
                statement.executeUpdate(
                    """
                        CREATE TABLE IF NOT EXISTS users(
                            id TEXT PRIMARY KEY,
                            username TEXT UNIQUE NOT NULL,
                            email TEXT UNIQUE NOT NULL,
                            password TEXT NOT NULL
                        );
                    """.trimIndent()
                )
                statement.executeUpdate(
                    """
                        CREATE TABLE IF NOT EXISTS accounts(
                            id TEXT PRIMARY KEY,
                            account_number TEXT UNIQUE NOT NULL,
                            owner_id TEXT NOT NULL,
                            balance REAL NOT NULL,
                            currency TEXT NOT NULL,
                            FOREIGN KEY(owner_id) REFERENCES users(id)
                        );
                    """.trimIndent()
                )
                statement.executeUpdate(
                    """
                        CREATE TABLE IF NOT EXISTS transactions(
                            id TEXT PRIMARY KEY,
                            sender_account TEXT NOT NULL,
                            receiver_account TEXT NOT NULL,
                            currency TEXT NOT NULL,
                            amount REAL NOT NULL,
                            type TEXT NOT NULL,
                            status TEXT NOT NULL
                        );
                    """.trimIndent()
                )
            }

        }
    }

    fun getConnection(): Connection = DriverManager.getConnection(DB_URL)
}