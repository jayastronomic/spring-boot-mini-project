package com.example.library.utils;

/**
 * The APIResponse class represents the response body structure for API responses.
 * It encapsulates the actual response data.
 *
 * @param responseBody The data or object to be included in the API response.
 */
public record APIResponse(Object responseBody) {}
