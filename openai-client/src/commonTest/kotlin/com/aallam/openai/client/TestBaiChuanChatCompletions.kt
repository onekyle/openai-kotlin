package com.aallam.openai.client

import com.aallam.openai.api.baichuan.BCCharacter
import com.aallam.openai.api.chat.*
import com.aallam.openai.api.model.ModelId
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.*

class TestBaiChuanChatCompletions : TestOpenAI() {
//
//    @Test
//    fun chatCompletions() = test {
//        val request = chatCompletionRequest {
//            model = ModelId("Baichuan-NPC-Turbo")
//            characterProfile = BCCharacter.character(id = 20306)
//            messages {
//                message {
//                    role = ChatRole.Assistant
//                    content = "呵, 你来了~"
//                }
//                message {
//                    role = ChatRole.User
//                    content = "你好呀"
//                }
//                message {
//                    role = ChatRole.Assistant
//                    content = "我很好"
//                }
//                message {
//                    role = ChatRole.User
//                    content = "想我没?"
//                }
//            }
//        }
//
//        val completion = openAI.chatCompletion(request)
//        assertTrue { completion.choices.isNotEmpty() }
//
//        val results = mutableListOf<ChatCompletionChunk>()
//        openAI.chatCompletions(request).onEach { results += it }.launchIn(this).join()
//
//        assertNotEquals(0, results.size)
//    }
//
//    @Test
//    fun chatCompletionsFunction() = test {
//        val modelId = ModelId("Baichuan-NPC-Turbo")
//        val chatMessages = mutableListOf(
//            ChatMessage(
//                role = ChatRole.User,
//                content = "What's the weather like in Boston?"
//            )
//        )
//
//        val request = chatCompletionRequest {
//            model = modelId
//            messages = chatMessages
//            tools {
//                function(
//                    name = "currentWeather",
//                    parameters =
//                    """
//                    {
//                      "type": "object",
//                      "properties": {
//                        "location": {
//                          "type": "string",
//                          "description": "The city and state, e.g. San Francisco, CA"
//                        },
//                        "unit": {
//                          "type": "string",
//                          "enum": [
//                            "celsius",
//                            "fahrenheit"
//                          ]
//                        }
//                      },
//                      "required": [
//                        "location"
//                      ]
//                    }
//                    """
//                )
//            }
//
//            toolChoice = ToolChoice.function("currentWeather")
//        }
//
//        val response = openAI.chatCompletion(request)
//        val message = response.choices.first().message
//        val toolCall = message.toolCalls?.first() as? ToolCall.Function
//        assertNotNull(toolCall)
//        assertEquals("currentWeather", toolCall.function?.name)
//    }
//
//    @Test
//    fun json() = test {
//        val request = chatCompletionRequest {
//            model = ModelId("Baichuan-NPC-Turbo")
//            responseFormat = ChatResponseFormat.JsonObject
//            characterProfile = BCCharacter.character(id = 20306)
//            messages {
//                message {
//                    role = ChatRole.Assistant
//                    content = "呵, 你来了~"
//                }
//                message {
//                    role = ChatRole.User
//                    content = "你好呀"
//                }
//                message {
//                    role = ChatRole.Assistant
//                    content = "我很好"
//                }
//                message {
//                    role = ChatRole.User
//                    content = "想我没?"
//                }
//            }
//        }
//        val response = openAI.chatCompletion(request)
//        val content = response.choices.first().message.content.orEmpty()
//
//        assertNotNull(content)
//    }
}
