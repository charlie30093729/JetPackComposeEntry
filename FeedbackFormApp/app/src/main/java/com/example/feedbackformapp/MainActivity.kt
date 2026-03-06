package com.example.feedbackformapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FeedbackFormApp()
        }
    }
}

@Composable
fun FeedbackFormApp() {
    var name by remember { mutableStateOf("") }
    var wasHelpful by remember { mutableStateOf(false) }
    var recommendation by remember { mutableStateOf("none") }
    var resultText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Your name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = wasHelpful,
                onCheckedChange = { wasHelpful = it }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("The lesson was helpful")
        }

        Text("Would you recommend this class?")

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = recommendation == "yes",
                    onClick = { recommendation = "yes" }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Yes")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = recommendation == "no",
                    onClick = { recommendation = "no" }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("No")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = recommendation == "not_sure",
                    onClick = { recommendation = "not_sure" }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Not sure")
            }
        }

        Button(
            onClick = {
                resultText =
                    "Thank you, $name.\nHelpful: $wasHelpful\nRecommendation: $recommendation"
            }
        ) {
            Text("Submit")
        }

        if (resultText.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(resultText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedbackFormPreview() {
    FeedbackFormApp()
}