package openai.azure.ui.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azure.ai.openai.models.Completions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import openai.azure.ui.services.OpenAIService

class MainViewModel(private val openAIService: OpenAIService): ViewModel() {

    private val _completions = MutableLiveData<Completions>()
    val completions: LiveData<Completions> get() = _completions

    fun getCompletions(prompt: String) {
        viewModelScope.launch {
            val completionsResponse = openAIService.getCompletions(prompt)
            _completions.value = completionsResponse
        }
    }

    fun asString(completions: Completions?): String = completions?.let { it.choices[0].text } ?: ""
}
