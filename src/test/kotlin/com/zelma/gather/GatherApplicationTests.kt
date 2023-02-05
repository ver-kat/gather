package com.zelma.gather

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GatherApplicationTest {
    @LocalServerPort
    lateinit var serverPort: String
    private val httpClient: HttpClient = HttpClient.create()

    fun createWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("http://localhost:$serverPort/api/hello")
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }

    @Test
    fun `test ping`() {
        val webClient = createWebClient()
        val response = webClient.get()
        assertNotNull(response)
    }
}
