package ir.arapp.arappmain.model

import ir.arapp.arappmain.model.base.Category
import ir.arapp.arappmain.model.base.Service

data class ServiceByCategory(
    var services: ArrayList<Service>, var category: Category
)