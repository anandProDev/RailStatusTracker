package com.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class FirstController {

    @RequestMapping("/answer")
    fun answer() = 42
}