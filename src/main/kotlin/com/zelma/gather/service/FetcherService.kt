package com.zelma.gather.service

import com.zelma.gather.model.definedResources
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class FetcherService(private val fetchers: List<Fetcher>) {
    fun getAll(resourceNames: Set<String>): Flux<Map<*, *>?> {
        return Flux.fromIterable(runBlocking {
            definedResources.filter {// Find the resources for the names
                resourceNames.contains(it.name)
            }.map {
                async {
                    fetchers.firstOrNull { fetcher -> // Find the fetcher that can fetch this type of resource
                        fetcher.supports(it)
                    }?.fetch(it) // Get the response for that resource
                }
            }.awaitAll()
        }).flatMap { it } // The Flux of all the responses
    }
}
