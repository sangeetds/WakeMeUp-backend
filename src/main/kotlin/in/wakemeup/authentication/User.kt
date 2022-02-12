package `in`.wakemeup.authentication

data class User(
  val id: Long,
  val name: String,
  val email: String,
  var loggedIn: Boolean,
)

val users =
  listOf(
    User(
      id = 1,
      name = "Sangeet",
      email = "sangeetnarayands@gmail.com",
      loggedIn = false
    )
  )