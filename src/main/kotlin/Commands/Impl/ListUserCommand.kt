package Commands.Impl

import Commands.Command
import Service.Bank

class ListUserCommand(
    val bank: Bank,
): Command {
    override val name = "list-user"
    override fun execute(args: List<String>) {
        if (args.size > 1) {
            println("Usage: list-user")
            return
        }

        bank.getAllUsers()
    }
}