package Commands.Impl

import Commands.Command
import Service.Bank

class AddAccountCommand(
    val bank: Bank,
): Command {
    override val name = "add-account"
    override fun execute(args : List<String>) {
        if (args.size != 1) {
            println("Error: add-account <username>")
            return
        }

        val username = args[0]
        bank.addAccount(username)
    }
}