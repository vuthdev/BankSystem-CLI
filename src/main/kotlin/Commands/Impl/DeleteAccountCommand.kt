package Commands.Impl

import Commands.Command
import Config.BankConfig
import Service.Bank

class DeleteAccountCommand(
    val bank: Bank,
): Command {
    override val name = "delete-account"
    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("delete-account <username>")
            return
        }
        if (args.size != 1) {
            println("delete-account <username>")
            return
        }

        val number = args[0].uppercase()

        bank.removeAccount(number)
    }
}