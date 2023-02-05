package com.zelma.gather.client

import com.zelma.gather.model.ResourceDefinition
import com.zelma.gather.model.RestResource
import com.zelma.gather.service.Fetcher
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.net.URI

@Component
class RestClient : Fetcher {
    companion object {
        private val logger = KotlinLogging.logger(this::class.java.name)
        private val webClient: WebClient = WebClient.builder().build() // Can make this a bean
    }

    override fun supports(resourceDefinition: ResourceDefinition): Boolean = resourceDefinition.resource is RestResource

    override fun fetch(resourceDefinition: ResourceDefinition): Mono<Map<*, *>>? {
        val resource = resourceDefinition.resource as RestResource
        val uriString = "${resource.uri}${resource.path}"
        logger.info("Fetching response at: ${uriString}")
        return webClient.get()
            .uri(URI(uriString))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Map::class.java)
            .onErrorResume {
                logger.error { "Error occurred for $uriString" }
                Mono.empty()
            }
    }
}
