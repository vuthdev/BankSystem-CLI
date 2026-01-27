package Model

import Model.Enums.Currency
import Model.Enums.TransactionStatus
import Model.Enums.TransactionType
import java.util.UUID

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val sender: String,
    val receiver: String,
    val amount: Double,
    val currency: Currency = Currency.USD,
    val type: TransactionType,
    val status: TransactionStatus,
)