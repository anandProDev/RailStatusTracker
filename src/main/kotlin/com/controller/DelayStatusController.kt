package com.controller

import com.domain.DelayedServiceHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DelayStatusController(val factory: PersistanceServiceFactory) {

    @GetMapping("/delays")
    fun getDelays(@RequestParam(value = "date") date: String) : DelayedServiceHolder {
        val details = factory.getPersistenceService().getDetails(date)
        return details
    }
}