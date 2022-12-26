package damin.tothemoon.missionanim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import damin.tothemoon.missionanim.ui.theme.MissionAnimTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MissionAnimTheme {
        val navController = rememberNavController()
        NavHost(
          navController,
          startDestination = "home"
        ) {
          composable(route = "home") {
            Home(
              onNavigate = { route ->
                navController.navigate(route) {
                  popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                  }
                  launchSingleTop = true
                  restoreState = true
                }
              }
            )
          }
          composable(route = MissionDestination.Memory.route) {
            Text(text = "Memory")
          }
        }
      }
    }
  }
}

@Composable
fun Home(modifier: Modifier = Modifier, onNavigate: (route: String) -> Unit = {}) {
  Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    LazyColumn(
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      items(MissionDestination.destinations) { destination ->
        DestinationButton(destination, onClick = onNavigate)
      }
    }
  }
}

@Preview
@Composable
fun AppPreview() {
  MissionAnimTheme {
    Home()
  }
}

@Composable
fun DestinationButton(
  destination: MissionDestination,
  modifier: Modifier = Modifier,
  onClick: (route: String) -> Unit = {},
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .clickable { onClick(destination.route) }
  ) {
    Row(
      modifier = Modifier.padding(16.dp)
    ) {
      Icon(destination.icon, contentDescription = null)
      Text(
        text = destination.title,
        modifier = Modifier
          .weight(1f)
          .padding(start = 8.dp)
      )
      Icon(Icons.Default.ArrowForwardIos, contentDescription = null)
    }
  }
}

@Preview
@Composable
fun DestinationButtonPreview() {
  MissionAnimTheme {
    DestinationButton(destination = MissionDestination.Math)
  }
}