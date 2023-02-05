package com.zelma.gather.model

import java.net.URI

val definedResources = listOf(
    ResourceDefinition(
        "swapiPeople", RestResource(URI("https://swapi.dev/api"), "/people?page=2&format=json")
    ),
    ResourceDefinition(
        "swapiPlanets", RestResource(URI("https://swapi.dev/api"), "/planets?page=2&format=json")
    )
)
