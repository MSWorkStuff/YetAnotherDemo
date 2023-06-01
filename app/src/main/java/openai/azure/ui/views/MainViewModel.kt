package openai.azure.ui.views

import androidx.lifecycle.ViewModel
import com.azure.ai.openai.models.Completions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import openai.azure.ui.services.OpenAIService

class MainViewModel(private val openAIService: OpenAIService): ViewModel() {

    private val _uiState = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    suspend fun getCompletions(prompt: String) {
        openAIService.getCompletions(prompt)
    }

}

sealed class UIState {
    object Loading: UIState()
    class CompletionsReady(val completions: Completions)

}