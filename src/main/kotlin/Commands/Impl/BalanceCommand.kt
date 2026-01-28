package Commands.Impl

import Commands.Command
import Service.Bank

class BalanceCommand(
    val bank: Bank,
): Command {
    override val name = "balance"
    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("Usage: balance <account-number>")
            return
        }
        if (args.size != 1) {
            return
        }

        val accountNumber = args[0].uppercase()
        bank.checkAccount(accountNumber)
    }
}