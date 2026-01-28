package Commands.Transaction

import Commands.Command
import Service.Bank

class WithdrawCommand(
    val bank: Bank,
): Command {
    override val name = "withdraw"
    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("Usage: withdraw <account-number> <amount>")
            return
        }

        if (args.size != 2) {
            println("Usage: withdraw <account-number> <amount>")
            return
        }

        val number = args[0].uppercase()
        val amount = args[1].toDoubleOrNull()
        if(amount == null) {
            println("Error: not a valid number")
            return
        }

        bank.withdraw(amount, number)
    }
}