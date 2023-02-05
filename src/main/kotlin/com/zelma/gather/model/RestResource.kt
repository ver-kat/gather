package com.zelma.gather.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonTypeName
import org.springframework.http.HttpMethod
import java.net.URI

@JsonTypeName("RestResource")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class RestResource(
    val uri: URI, val path: String, val method: HttpMethod = HttpMethod.GET
) : ResourceType
