package Util

import DB.Database

object AccountNumberGenerator {
    fun generateAccountNumber(): String {
        Database.getConnection().use { conn ->
            val stmt = conn.createStatement()
            val rs = stmt.executeQuery("SELECT account_number from accounts order by account_number desc limit 1")

            val lastNumber = if (rs.next()) {
                val lastAcc = rs.getString("account_number")
                lastAcc.removePrefix("ACC").toInt()
            } else 100000
            val newNumber = lastNumber + 1
            return "ACC$newNumber"
        }
    }
}