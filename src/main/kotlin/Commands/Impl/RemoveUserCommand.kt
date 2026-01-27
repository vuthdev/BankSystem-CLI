package Commands.Impl

import Commands.Command
import Service.Bank

class RemoveUserCommand(
    val bank: Bank,
): Command {
    override val name = "remove-user"
    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("Error: remove-user <username>")
            return
        }

        if (args.size != 1) {
            println("Error: remove-user <username>")
            return
        }

        val username = args[0].lowercase()
        bank.removeUser(username)
    }
}