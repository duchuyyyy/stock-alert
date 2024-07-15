package com.phdhuy.springhexagonaltemplate.application.rest.controller.crypto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cryptos")
@RequiredArgsConstructor
@Tag(name = "Cryptos APIs")
public class CryptoController {
}
