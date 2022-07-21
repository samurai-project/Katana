package com.qarva.feature.messenger

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Messages") },
                navigationIcon = {
                    IconButton(onClick = navController::popBackStack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = modifier.fillMaxSize()
        ) {
            items(25) {
                Row(
                    modifier = modifier
                        .padding(all = 11.dp)
                        .fillMaxSize()
                        .clickable {  }
                ) {
                    AsyncImage(
                        model = "https://loremflickr.com/536/350",
                        contentDescription = "Contact profile picture",
                        modifier = modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                    )

                    Spacer(modifier = modifier.width(10.dp))

                    Column {
                        Text(text = "Hitler", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = modifier.height(2.dp))
                        Text(text = "Heil hitler", style = MaterialTheme.typography.titleSmall)
                    }
                }
            }
        }
    }

}
