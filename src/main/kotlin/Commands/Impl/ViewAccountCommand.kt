package Commands.Impl

import Commands.Command
import Service.Bank

class ViewAccountCommand(
    val bank: Bank,
): Command {
    override val name = "view-account"
    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("Usage: view-account <account-number>")
            return
        }
        if (args.size != 1) {
            return
        }

        val accountNumber = args[0].uppercase()
        bank.checkAccount(accountNumber)
    }
}