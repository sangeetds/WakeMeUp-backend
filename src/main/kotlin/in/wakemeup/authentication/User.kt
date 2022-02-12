package `in`.wakemeup.authentication

import `in`.wakemeup.bank.BankAccount
import `in`.wakemeup.bank.BankBalance
import kotlinx.datetime.LocalDateTime

data class User(
  val id: Long,
  val name: String,
  val email: String,
  var loggedIn: Boolean,
  val bankAccount: BankAccount?
)

val users =
  listOf(
    User(
      id = 1,
      name = "Sangeet",
      email = "sangeetnarayands@gmail.com",
      loggedIn = false,
      bankAccount = BankAccount(
        id = 1,
        accountNumber = 213456,
        accountType = "Savings",
        balances = listOf(
          BankBalance(
            id = 1,
            dateObserved = LocalDateTime(2022, 1, 1, 0, 0, 0),
            amount = 1000
          )
        )
      )
    )
  )