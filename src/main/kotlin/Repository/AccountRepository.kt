package Repository

import DB.Database
import Model.Account
import Model.Enums.Currency
import Model.User
import java.util.UUID

class AccountRepository {
    fun save(account: Account) {
        val sql = "INSERT INTO accounts(id, account_number, owner_id, balance, currency) VALUES (?, ?, ?, ?, ?)"

        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setObject(1, account.id)
                stmt.setString(2, account.accountNumber)
                stmt.setObject(3, account.ownerId)
                stmt.setDouble(4, account.balance)
                stmt.setString(5, account.currency.name)
                stmt.executeUpdate()
            }
        }
    }

    fun updateBalance(account: Account, balance: Double) {
        val sql = "UPDATE accounts SET balance = ? WHERE id = ?"
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setDouble(1, balance)
                stmt.setObject(2, account.id)
                stmt.executeUpdate()
            }
        }
    }

    fun findAccountByNumber(accountNumber: String): Account? {
        val sql = "SELECT * FROM accounts WHERE account_number = ?"

        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, accountNumber)
                val rs = stmt.executeQuery()
                return if (rs.next()) {
                    Account(
                        id = UUID.fromString(rs.getString("id")),
                        accountNumber = rs.getString("account_number"),
                        ownerId = UUID.fromString(rs.getString("owner_id")),
                        balance = rs.getDouble("balance"),
                        currency = Currency.valueOf(rs.getString("currency"))
                    )
                } else null
            }
        }
    }

    fun findAccountByUserId(userId: UUID): List<Account> {
        val accountList = mutableListOf<Account>()
        val sql = "SELECT * FROM accounts WHERE owner_id = ?"
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, userId.toString())
                val rs = stmt.executeQuery()
                while (rs.next()) {
                    accountList.add(
                        Account(
                            id = UUID.fromString(rs.getString("id")),
                            accountNumber = rs.getString("account_number"),
                            ownerId = UUID.fromString(rs.getString("owner_id")),
                            balance = rs.getDouble("balance"),
                            currency = Currency.valueOf(rs.getString("currency"))
                        )
                    )
                }
            }
        }
        return accountList
    }

    fun countAllAccounts(username: String): Int {
        val sql = "SELECT COUNT(*) FROM accounts a join users u on a.owner_id = u.id WHERE u.username = ?"

        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, username)
                val rs = stmt.executeQuery()
                return if (rs.next()) {
                    rs.getInt(1)
                } else 0
            }
        }
    }

    fun totalBalanceByUsername(username: String): Double {
        val sql = "SELECT SUM(balance) from accounts a JOIN users u on a.owner_id = u.id WHERE u.username = ?"
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, username)
                val rs = stmt.executeQuery()
                return if (rs.next()) {
                    rs.getDouble(1)
                } else 0.0
            }
        }
    }

    fun deleteByAccountNumber(accountNumber: String) {
        val sql = "DELETE FROM accounts WHERE account_number = ?"
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, accountNumber)
                stmt.executeUpdate()
            }
        }
    }

    fun deleteByUserId(userId: User) {
        val sql = "DELETE FROM accounts WHERE owner_id = ?"
        Database.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, userId.toString())
                stmt.executeUpdate()
            }
        }
    }
}