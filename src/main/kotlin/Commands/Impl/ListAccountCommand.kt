package Commands.Impl

import Commands.Command
import Service.Bank

class ListAccountCommand(
    val bank: Bank,
): Command {
    override val name = "list-account"
    override fun execute(args : List<String>)  {
        if (args.isEmpty()) {
            println("list-account <username>")
            return
        }

        if (args.size != 1) {
            return
        }

        val username = args[0].lowercase()
        bank.listAccountsByUsername(username)
    }
}