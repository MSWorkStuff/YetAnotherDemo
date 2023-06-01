@file:OptIn(ExperimentalMaterial3Api::class)

package openai.azure.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import openai.azure.R
import openai.azure.ui.services.OpenAIService
import openai.azure.ui.theme.JavaDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val openAIService = OpenAIService()
        val viewModel = MainViewModel(openAIService)

        super.onCreate(savedInstanceState)
        setContent {
            JavaDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    CenterColumn(viewModel)
                }
            }
        }
    }
}

@Composable
fun CenterColumn(viewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        val completions = viewModel.completions.observeAsState()


        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text(text = "Prompt") },
            placeholder = { Text(text = stringResource(id = R.string.description_label)) },
        )
        
        Button(
            onClick = { viewModel.getCompletions(text.text) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Submit")
        }

        Text(
            text = viewModel.asString(completions.value),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val openAIService = OpenAIService()
    val viewModel = MainViewModel(openAIService)

    JavaDemoTheme {
        CenterColumn(viewModel)
    }
}