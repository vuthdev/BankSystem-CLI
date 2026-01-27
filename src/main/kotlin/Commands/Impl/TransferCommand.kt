package Commands.Impl

import Commands.Command
import Service.Bank
import Util.BankValidator

class TransferCommand(
    val bank: Bank,
): Command {
    override val name = "transfer"
    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("Usage: transfer <account-number> <account-number> <amount>")
            return
        }

        if (args.size != 3) {
            println("Usage: transfer <account-number> <account-number> <amount>")
            return
        }

        val sender = args[0].uppercase()
        val receiver = args[1].uppercase()
        val amount = args[2].toDoubleOrNull()
        if (amount == null) {
            println("Error: not a valid number")
            return
        }

        bank.transfer(amount, sender, receiver)
    }
}