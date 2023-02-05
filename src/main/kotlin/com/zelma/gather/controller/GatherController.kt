package com.zelma.gather.controller

import com.zelma.gather.service.FetcherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api")
class GatherController(private val fetcherService: FetcherService) {

    @GetMapping("/hello") // Just here to make sure things are working
    fun getHello(): String? {
        return "Hello"
    }

    @GetMapping("/result") // Param is the name of the defined resources to get responses for
    fun getResult(@RequestParam resourceNames: Set<String>): Flux<Map<*, *>?> {
        return fetcherService.getAll(resourceNames)
    }
}
