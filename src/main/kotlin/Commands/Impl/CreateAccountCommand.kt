package Commands.Impl

import Commands.Command
import Service.Bank

class CreateAccountCommand(
    val bank: Bank,
): Command {
    override val name = "create-account"
    override fun execute(args : List<String>) {
        if (args.size != 1) {
            println("Error: create-account <username>")
            return
        }

        val username = args[0]
        bank.addAccount(username)
    }
}