package com.zelma.gather.service

import com.zelma.gather.model.ResourceDefinition
import reactor.core.publisher.Mono

interface Fetcher {
    fun supports(resourceDefinition: ResourceDefinition): Boolean
    fun fetch(resourceDefinition: ResourceDefinition): Mono<Map<*, *>>?
}
