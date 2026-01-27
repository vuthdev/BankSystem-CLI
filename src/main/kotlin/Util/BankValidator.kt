package Util

object BankValidator {
    fun isValidAmount(amount: Double): Boolean {
        return amount > 0
    }
}