# 🏦 Bank System (CLI Application)

A command-line banking system built to practice backend fundamentals such as system design, business logic, and transaction flow.

---

## 📌 Available Commands

### 👤 User Commands

| Command                  | Description                |
| ------------------------ | -------------------------- |
| `create-user`            | Create a new user          |
| `list-users`             | Show all users             |
| `delete-user <username>` | Delete an existing user    |

### 💳 Account Commands

| Command                               | Description                                 |
| ------------------------------------- | ------------------------------------------- |
| `create-account <username>`           | Create a new bank account for selected user |
| `list-account <username>`             | Show all accounts of selected user          |
| `delete-account <account-num>`        | Delete an account by number                 |
| `balance <account-num>`               | Show current account balance                |

### 💸 Transaction Commands

| Command                                           | Description                       |
| ------------------------------------------------- | --------------------------------- |
| `deposit <account-num> <amt>`                     | Deposit money into account        |
| `withdraw <account-num> <amt>`                    | Withdraw money from account       |
| `transfer <sender-num> <recv-num> <amt>`          | Transfer money between account    |

---

### 💰 Deposit (បញ្ចូលប្រាក់)

* **Meaning:** Adding money into a bank account.
* **Everyday meaning:** Putting money into your own account.
* **Effect:** Account balance increases.

**Example:**

> បញ្ចូលប្រាក់ ១០០ ដុល្លារ
> (Deposit $100 into the account)

---

### 💸 Withdrawal (ដកប្រាក់)

* **Meaning:** Taking money out from a bank account.
* **Everyday meaning:** Removing money from your account.
* **Effect:** Account balance decreases.

**Example:**

> ដកប្រាក់ ៥០ ដុល្លារ
> (Withdraw $50 from the account)

---

### 🔁 Transfer (ផ្ទេរប្រាក់)

* **Meaning:** Moving money from one bank account to another.
* **Everyday meaning:** Sending money to someone else or another account.
* **Effect:**

  * Sender account → balance decreases
  * Receiver account → balance increases

**Example:**

> ផ្ទេរប្រាក់ពីគណនី A ទៅគណនី B
> (Transfer money from account A to account B)

---
