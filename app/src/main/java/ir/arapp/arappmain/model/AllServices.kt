package ir.arapp.arappmain.model

import ir.arapp.arappmain.model.base.Service

class AllServices {
    var Success: Boolean = false
    var message = ""
    var result = Result()
    class Result {
        var services = ArrayList<Service>()
    }
}