package `in`.wakemeup.users

import kotlinx.serialization.Serializable

@Serializable
data class BuddyUser(
  val id: Long,
  val name: String,
  val email: String
)