package com.app.restApiAndroid.models.dots;

import org.springframework.http.HttpStatus;

public record ValidationsDTO(Boolean valid, String message, HttpStatus statusCode) {
}
