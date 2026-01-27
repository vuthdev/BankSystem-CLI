package Repository

import DB.Database
import Model.Enums.Currency
import Model.Enums.TransactionStatus
import Model.Enums.TransactionType
import Model.Transaction
import java.util.UUID

class TransactionRepository {
    fun save(transaction: Transaction) {
        val sql = """
            INSERT INTO 
            transactions(id, sender_account, receiver_account, amount, currency, type, status)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { ps ->
                ps.setObject(1, transaction.id)
                ps.setString(2, transaction.sender)
                ps.setString(3, transaction.receiver)
                ps.setDouble(4, transaction.amount)
                ps.setString(5, transaction.currency.name)
                ps.setString(6, transaction.type.name)
                ps.setString(7, transaction.status.name)
                ps.executeUpdate()
            }
        }
    }

    fun getAll(): List<Transaction> {
        val transactions = mutableListOf<Transaction>()

        val sql = """SELECT * FROM transactions ORDER BY sender_account DESC LIMIT 1;"""
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { ps ->
                val rs = ps.executeQuery()
                while (rs.next()) {
                    transactions.add(
                        Transaction(
                            id = UUID.fromString(rs.getString("id")),
                            sender = rs.getString("sender_account"),
                            receiver = rs.getString("receiver_account"),
                            amount = rs.getDouble("amount"),
                            currency = Currency.valueOf(rs.getString("currency")),
                            type = TransactionType.valueOf(rs.getString("type")),
                            status = TransactionStatus.valueOf(rs.getString("status")),
                        )
                    )
                }
            }
        }
        return transactions
    }
}