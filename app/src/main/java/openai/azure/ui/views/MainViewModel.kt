package openai.azure.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azure.ai.openai.models.Completions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import openai.azure.ui.services.OpenAIService

class MainViewModel(private val openAIService: OpenAIService): ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    fun getCompletions(prompt: String) {
        viewModelScope.launch {
            _uiState.emit(UIState.Loading)
            val completions = openAIService.getCompletions(prompt)
            _uiState.emit(UIState.CompletionsReady(completions))
        }
    }

}

sealed class UIState {
    object Loading: UIState()
    class CompletionsReady(val completions: Completions): UIState()

}