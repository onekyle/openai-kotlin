package com.aallam.openai.client

import com.aallam.openai.api.http.Timeout
import com.aallam.openai.client.internal.BaiChuanAPI
import com.aallam.openai.client.internal.createHttpClient
import com.aallam.openai.client.internal.http.HttpTransport
import io.ktor.client.*
import kotlin.time.Duration.Companion.seconds

/**
 * OpenAI API.
 */
public interface BaiChuan : Completions, Files, Edits, Embeddings, Models, Moderations, FineTunes, Images, Chat, Audio,
    FineTuning, Assistants, Threads, Runs, Messages, Closeable

/**
 * Creates an instance of [OpenAI].
 *
 * @param token secret API key
 * @param logging client logging configuration
 * @param timeout http client timeout
 * @param headers extra http headers
 * @param organization OpenAI organization ID
 * @param host OpenAI host configuration
 * @param proxy HTTP proxy url
 * @param host OpenAI host configuration.
 * @param retry rate limit retry configuration
 * @param httpClientConfig additional custom client configuration
 */
public fun BaiChuan(
    token: String,
    logging: LoggingConfig = LoggingConfig(),
    timeout: Timeout = Timeout(socket = 30.seconds),
    organization: String? = null,
    headers: Map<String, String> = emptyMap(),
    host: OpenAIHost = OpenAIHost.BaiChuan,
    proxy: ProxyConfig? = null,
    retry: RetryStrategy = RetryStrategy(),
    httpClientConfig: HttpClientConfig<*>.() -> Unit = {}
): BaiChuan = BaiChuan(
    config = OpenAIConfig(
        token = token,
        logging = logging,
        timeout = timeout,
        organization = organization,
        headers = headers,
        host = host,
        proxy = proxy,
        retry = retry,
        httpClientConfig = httpClientConfig,
    )
)

/**
 * Creates an instance of [OpenAI].
 *
 * @param config client config
 */
public fun BaiChuan(config: OpenAIConfig): BaiChuan {
    val httpClient = createHttpClient(config)
    val transport = HttpTransport(httpClient)
    return BaiChuanAPI(transport)
}
