//package com.controller
//
//import com.db.PersistanceService
//import com.domain.DelayedServiceHolder
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Qualifier
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//class DelayStatusController(@Qualifier(value="mongo-db") persistanceService: PersistanceService) {
//
//    var persistanceService = persistanceService
//
//    @GetMapping("/delays")
//    fun getDelays(@RequestParam(value = "date") date: String) : DelayedServiceHolder {
//        val details = persistanceService.getDetails(date)
//
//        return details
//    }
//}