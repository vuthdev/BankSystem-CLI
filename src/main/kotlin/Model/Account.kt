package Model

import Model.Enums.Currency
import Util.AccountNumberGenerator
import java.util.UUID

data class Account(
    val id: UUID = UUID.randomUUID(),
    var accountNumber: String = AccountNumberGenerator.generateAccountNumber(),
    var ownerId: UUID,
    var balance: Double = 0.0,
    var currency: Currency = Currency.USD,
) {
    fun credit(amount: Double) {
        this.balance += amount
    }

    fun debit(amount: Double) {
        this.balance -= amount
    }
}