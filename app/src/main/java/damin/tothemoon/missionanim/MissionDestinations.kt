package damin.tothemoon.missionanim

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MissionDestination {
  object Math : MissionDestination() {
    override val icon: ImageVector = Icons.Default.Calculate
    override val route: String = "math"
    override val title: String = "수학"
  }

  object Memory: MissionDestination() {
    override val icon: ImageVector = Icons.Default.Dataset
    override val route: String = "memory"
    override val title: String = "메모리"
  }

  abstract val icon: ImageVector
  abstract val route: String
  abstract val title: String

  companion object {
    val destinations: Array<MissionDestination>
      get() = arrayOf(Math, Memory)
  }
}