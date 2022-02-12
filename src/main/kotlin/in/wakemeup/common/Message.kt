package `in`.wakemeup.common

import `in`.wakemeup.users.BuddyUser
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Message(
  val user: BuddyUser,
  val time: LocalDateTime,
  val alarmTitle: String,
)
