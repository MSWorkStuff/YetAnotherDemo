package openai.azure.ui.services

import com.azure.ai.openai.OpenAIClient
import com.azure.ai.openai.OpenAIClientBuilder
import com.azure.ai.openai.models.Completions
import com.azure.core.credential.AzureKeyCredential
import kotlinx.coroutines.coroutineScope
import openai.azure.BuildConfig

class OpenAIService {

    private val openAIClient: OpenAIClient =
        OpenAIClientBuilder()
            .endpoint(BuildConfig.AZURE_OPENAI_ENDPOINT)
            .credential(AzureKeyCredential(BuildConfig.AZURE_OPENAI_KEY))
            .buildClient()

    suspend fun getCompletions(prompt:String ) : Completions {
        return coroutineScope {
            openAIClient.getCompletions("text-davinci-003", prompt)
        }
    }
}