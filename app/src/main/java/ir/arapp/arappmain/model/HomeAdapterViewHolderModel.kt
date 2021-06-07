package ir.arapp.arappmain.model

import ir.arapp.arappmain.model.base.Banner

class HomeAdapterViewHolderModel(var viewType: ViewType) {
    enum class ViewType {
        BANNER, SERVICE_BY_CATEGORY
    }
    var bannerModel: ArrayList<Banner>? = null
    var serviceByCategoryModel: ServiceByCategory? = null
}