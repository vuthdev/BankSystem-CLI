package Commands.Transaction

import Commands.Command
import Service.Bank

class ListTransactionsCommand(
    val bank: Bank,
): Command {
    override val name = "list-transaction"
    override fun execute(args: List<String>) {
        if (args.size > 1) {
            println("Usage: list-transaction")
            return
        }

        bank.listTransactions()
    }
}