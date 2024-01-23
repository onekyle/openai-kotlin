@file:Suppress("DEPRECATION")

package com.aallam.openai.api.chat

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.OpenAIDsl
import com.aallam.openai.api.baichuan.BCCharacter
import com.aallam.openai.api.core.Parameters
import com.aallam.openai.api.model.ModelId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObjectBuilder

/**
 * Creates a completion for the chat message.
 */
@Serializable
public class ChatCompletionRequest(
    /**
     * ID of the model to use.
     */
    @SerialName("model") public val model: ModelId,

    /**
     * The messages to generate chat completions for.
     */
    @SerialName("messages") public val messages: List<ChatMessage>,

    @SerialName("character_profile") public val characterProfile: BCCharacter? = null,

    /**
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random,
     * while lower values like 0.2 will make it more focused and deterministic.
     *
     * We generally recommend altering this or [topP] but not both.
     */
    @SerialName("temperature") public val temperature: Double? = null,

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results
     * of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass
     * are considered.
     *
     * We generally recommend altering this or [temperature] but not both.
     */
    @SerialName("top_p") public val topP: Double? = null,

    /**
     * How many chat completion choices to generate for each input message.
     */
    @SerialName("n") public val n: Int? = null,

    /**
     * Up to 4 sequences where the API will stop generating further tokens.
     */
    @SerialName("stop") public val stop: List<String>? = null,

    /**
     * The maximum number of tokens allowed for the generated answer. By default, the number of tokens the model can
     * return will be (4096 - prompt tokens).
     */
    @SerialName("max_tokens") public val maxTokens: Int? = null,

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far,
     * increasing the model's likelihood to talk about new topics.
     *
     * [Read more](https://platform.openai.com/docs/api-reference/parameter-details)
     */
    @SerialName("presence_penalty") public val presencePenalty: Double? = null,

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so
     * far, decreasing the model's likelihood to repeat the same line verbatim.
     *
     * [Read more](https://platform.openai.com/docs/api-reference/parameter-details)
     */
    @SerialName("frequency_penalty") public val frequencyPenalty: Double? = null,

    /**
     * Modify the likelihood of specified tokens appearing in the completion.
     *
     * Accepts a json object that maps tokens (specified by their token ID in the tokenizer) to an associated bias value
     * from -100 to 100. Mathematically, the bias is added to the logits generated by the model prior to sampling.
     * The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of
     * selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.
     */
    @SerialName("logit_bias") public val logitBias: Map<String, Int>? = null,

    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     */
    @SerialName("user") public val user: String? = null,

    /**
     * A list of functions the model may generate JSON inputs for.
     */
    @Deprecated(message = "Deprecated in favor of tools")
    @SerialName("functions") public val functions: List<ChatCompletionFunction>? = null,

    /**
     * Controls how the model responds to function calls. [FunctionMode.None] means the model does not call a function,
     * and responds to the end-user.
     * [FunctionMode.Auto] means the model can pick between an end-user or calling a function.
     * Specifying a particular function via [FunctionMode.Named] forces the model to call that function.
     * [FunctionMode.None] is the default when no functions are present.
     * [FunctionMode.Auto] is the default if functions are present.
     */
    @Deprecated(message = "Deprecated in favor of ToolChoice")
    @SerialName("function_call") public val functionCall: FunctionMode? = null,

    /**
     * An object specifying the format that the model must output.
     *
     * Setting to [ChatResponseFormat.JsonObject] enables JSON mode, which guarantees the message the model generates is
     * valid JSON.
     *
     * **Important**: when using JSON mode, you must still instruct the model to produce JSON yourself via some
     * conversation message, for example, via your system message. If you don't do this, the model may generate
     * an unending stream of whitespace until the generation reaches the token limit, which may take a lot of time
     * and give the appearance of a "stuck" request. Also note that the message content may be partial (i.e., cut off)
     * if finish_reason="length", which indicates the generation exceeded `max_tokens` or the conversation exceeded
     * the max context length.
     */
    @SerialName("response_format") public val responseFormat: ChatResponseFormat? = null,

    /**
     * A list of tools the model may call. Use this to provide a list of functions the model may generate JSON inputs for.
     */
    @SerialName("tools") public val tools: List<Tool>? = null,

    /**
     * Controls which (if any) function is called by the model.
     *
     * - [ToolChoice.None] means the model will not call a function and instead generates a message.
     * - [ToolChoice.Auto] means the model can pick between generating a message or calling a function.
     * - Specifying a particular function via [ToolChoice.Named] (or [ToolChoice.function]) forces the model to call that function.
     *
     * [ToolChoice.None] is the default when no functions are present.[ToolChoice.Auto] is the default if functions are
     * present.
     */
    @SerialName("tool_choice") public val toolChoice: ToolChoice? = null,

    /**
     * If specified, our system will make the best effort to sample deterministically, such that repeated requests with
     * the same seed and parameters should return the same result.
     * Determinism is not guaranteed, and you should refer to the `systemFingerprint` response parameter to monitor
     * changes in the backend.
     */
    @property:BetaOpenAI
    @SerialName("seed") public val seed: Int? = null,
)

/**
 * The messages to generate chat completions for.
 */
public fun chatCompletionRequest(block: ChatCompletionRequestBuilder.() -> Unit): ChatCompletionRequest =
    ChatCompletionRequestBuilder().apply(block).build()

/**
 * Creates a completion for the chat message.
 */
@OpenAIDsl
public class ChatCompletionRequestBuilder {
    /**
     * ID of the model to use.
     */
    public var model: ModelId? = null

    /**
     * The messages to generate chat completions for.
     */
    public var messages: List<ChatMessage>? = null


    public var characterProfile: BCCharacter? = null
    /**
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random,
     * while lower values like 0.2 will make it more focused and deterministic.
     *
     * We generally recommend altering this or [topP] but not both.
     */
    public var temperature: Double? = null

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results
     * of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass
     * are considered.
     *
     * We generally recommend altering this or [temperature] but not both.
     */
    public var topP: Double? = null

    /**
     * How many chat completion choices to generate for each input message.
     */
    public var n: Int? = null

    /**
     * Up to 4 sequences where the API will stop generating further tokens.
     */
    public var stop: List<String>? = null

    /**
     * The maximum number of tokens allowed for the generated answer. By default, the number of tokens the model can
     * return will be (4096 - prompt tokens).
     */
    public var maxTokens: Int? = null

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far,
     * increasing the model's likelihood to talk about new topics.
     *
     * [Read more](https://platform.openai.com/docs/api-reference/parameter-details)
     */
    public var presencePenalty: Double? = null

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so
     * far, decreasing the model's likelihood to repeat the same line verbatim.
     *
     * [Read more](https://platform.openai.com/docs/api-reference/parameter-details)
     */
    public var frequencyPenalty: Double? = null

    /**
     * Modify the likelihood of specified tokens appearing in the completion.
     *
     * Accepts a json object that maps tokens (specified by their token ID in the tokenizer) to an associated bias value
     * from -100 to 100. Mathematically, the bias is added to the logits generated by the model prior to sampling.
     * The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of
     * selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.
     */
    public var logitBias: Map<String, Int>? = null

    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     */
    public var user: String? = null

    /**
     * A list of functions the model may generate JSON inputs for.
     */
    @Deprecated(message = "Deprecated in favor of tools")
    public var functions: List<ChatCompletionFunction>? = null

    /**
     * Controls how the model responds to function calls. [FunctionMode.None] means the model does not call a function,
     * and responds to the end-user.
     * [FunctionMode.Auto] means the model can pick between an end-user or calling a function.
     * Specifying a particular function via [FunctionMode.Named] forces the model to call that function.
     * [FunctionMode.None] is the default when no functions are present.
     * [FunctionMode.Auto] is the default if functions are present.
     */
    @Deprecated(message = "Deprecated in favor of ToolChoice")
    public var functionCall: FunctionMode? = null

    /**
     * An object specifying the format that the model must output.
     *
     * Setting to [ChatResponseFormat.JsonObject] enables JSON mode, which guarantees the message the model generates is
     * valid JSON.
     *
     * **Important**: when using JSON mode you must still instruct the model to produce JSON yourself via some
     * conversation message, for example via your system message. If you don't do this, the model may generate
     * an unending stream of whitespace until the generation reaches the token limit, which may take a lot of time
     * and give the appearance of a "stuck" request. Also note that the message content may be partial (i.e. cut off)
     * if finish_reason="length", which indicates the generation exceeded `max_tokens` or the conversation exceeded
     * the max context length.
     */
    public var responseFormat: ChatResponseFormat? = null

    /**
     * A list of tools the model may call. Use this to provide a list of functions the model may generate JSON inputs for.
     */
    public var tools: List<Tool>? = null

    /**
     * Controls which (if any) function is called by the model.
     *
     * - [ToolChoice.None] means the model will not call a function and instead generates a message.
     * - [ToolChoice.Auto] means the model can pick between generating a message or calling a function.
     * - Specifying a particular function via [ToolChoice.Named] (or [ToolChoice.function]) forces the model to call that function.
     *
     * [ToolChoice.None] is the default when no functions are present.[ToolChoice.Auto] is the default if functions are
     * present.
     */
    public var toolChoice: ToolChoice? = null

    /**
     * The messages to generate chat completions for.
     */
    public fun messages(block: ChatMessagesBuilder.() -> Unit) {
        messages = ChatMessagesBuilder().apply(block).messages
    }

    /**
     * A list of tools the model may call. Use this to provide a list of functions the model may generate JSON inputs for.
     */
    public fun tools(block: ToolBuilder.() -> Unit) {
        tools = ToolBuilder().apply(block).functions
    }

    /**
     * A list of functions the model may generate JSON inputs for.
     */
    @Deprecated(message = "Deprecated in favor of tools")
    public fun functions(block: FunctionsBuilder.() -> Unit) {
        functions = FunctionsBuilder().apply(block).functions
    }

    /**
     * Builder of [ChatCompletionRequest] instances.
     */
    public fun build(): ChatCompletionRequest = ChatCompletionRequest(
        model = requireNotNull(model) { "model is required" },
        messages = requireNotNull(messages) { "messages is required" },
        characterProfile = characterProfile,
        temperature = temperature,
        topP = topP,
        n = n,
        stop = stop,
        maxTokens = maxTokens,
        presencePenalty = presencePenalty,
        frequencyPenalty = frequencyPenalty,
        logitBias = logitBias,
        user = user,
        functions = functions,
        functionCall = functionCall,
        responseFormat = responseFormat,
        toolChoice = toolChoice,
        tools = tools
    )
}

/**
 * Creates a list of [ChatMessage].
 */
public class ChatMessagesBuilder {
    internal val messages = mutableListOf<ChatMessage>()

    /**
     * Creates a [ChatMessage] instance.
     */
    public fun message(block: ChatMessageBuilder.() -> Unit) {
        messages += ChatMessageBuilder().apply(block).build()
    }

    /**
     * System chat message.
     */
    public fun system(block: SystemMessageBuilder.() -> Unit) {
        this.messages += SystemMessageBuilder().apply(block).build()
    }

    /**
     * User chat message.
     */
    public fun user(block: UserMessageBuilder.() -> Unit) {
        this.messages += UserMessageBuilder().apply(block).build()
    }

    /**
     * Assistant chat message.
     */
    public fun assistant(block: AssistantMessageBuilder.() -> Unit) {
        this.messages += AssistantMessageBuilder().apply(block).build()
    }

    /**
     * Tool chat message.
     */
    public fun tool(block: ToolMessageBuilder.() -> Unit) {
        this.messages += ToolMessageBuilder().apply(block).build()
    }
}

/**
 * Creates a list of [ChatCompletionFunction].
 */
public class FunctionsBuilder {
    internal val functions = mutableListOf<ChatCompletionFunction>()

    /**
     * Creates a [ChatMessage] instance.
     */
    public fun function(block: ChatCompletionFunctionBuilder.() -> Unit) {
        functions += ChatCompletionFunctionBuilder().apply(block).build()
    }
}

/**
 * Creates a list of [Tool].
 */
public class ToolBuilder {
    internal val functions = mutableListOf<Tool>()

    /**
     * Creates a [ChatMessage] instance.
     */
    public fun tool(tool: Tool) {
        functions += tool
    }

    /**
     * Creates a 'function' tool.
     *
     * @param name The name of the function to be called. Must be a-z, A-Z, 0-9, or contain underscores and dashes,
     * with a maximum length of 64.
     * @param parameters The parameters the function accepts, described as a JSON Schema object.
     */
    public fun function(name: String, description: String? = null, parameters: Parameters) {
        functions += Tool.function(name, description, parameters)
    }

    /**
     * Creates a 'function' tool.
     *
     * @param name The name of the function to be called. Must be a-z, A-Z, 0-9, or contain underscores and dashes,
     * with a maximum length of 64.
     * @param parameters The parameters the function accepts, described as a JSON Schema object.
     */
    public fun function(name: String, description: String? = null, parameters: String) {
        functions += Tool.function(name, description, Parameters.fromJsonString(parameters))
    }

    /**
     * Creates a 'function' tool.
     *
     * @param name The name of the function to be called. Must be a-z, A-Z, 0-9, or contain underscores and dashes,
     * with a maximum length of 64.
     * @param parameters The parameters the function accepts, described as a JSON Schema object.
     */
    public fun function(name: String, description: String? = null, parameters: JsonObjectBuilder.() -> Unit) {
        functions += Tool.function(name, description, Parameters.buildJsonObject(parameters))
    }
}
