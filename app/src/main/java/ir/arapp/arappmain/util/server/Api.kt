package ir.arapp.arappmain.util.server

import ir.arapp.arappmain.model.AllServices
import ir.arapp.arappmain.model.base.GetService
import ir.arapp.arappmain.model.base.ResponseModel
import ir.arapp.arappmain.model.base.SendService
import ir.arapp.arappmain.model.base.Service
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {
    companion object {
        val BaseUrl = "http://arrapp.herokuapp.com/"
        var userToken =
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZWIxM2RiMmYyZmM4MzJjYzZiMmRiM2M1NzJjNWE2NGE2ZWNkYzJiZjh" +
                    "mMmI5ZTRhNGVmZTU2NzExY2VlNzU0YWYxM2U0MDk4ZGJlYjU5ODkiLCJpYXQiOjE2MjY1MDI1NTkuMjMzMzI5LCJuYmYiOjE2MjY1MDI1NTkuMjMzMzMz" +
                    "LCJleHAiOjE2NTgwMzg1NTkuMjI4NTg2LCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.aL6x2P5FpAU9Uktq4lYc0RolXBCaaiX87y0R9us9hBYnI27ISiZ4Pc" +
                    "ZcDclz5OHPsd3Kt0wLM8JyvrVBq0_A_SOb_KyZwWjB83UESO_B1J1nT7cPmN-7iyhHDyY742WwwckEohNaYwQBUsBFi5Z9qMmvha0d66z5VIO7GBS8c8TXn-m" +
                    "PJe1inCbVLXyYwEha5AiG6hNfo7_zE4nLroQ3bAE3SiDpqbDvMQwzSNuyPRSJYiPV6APz2iAg0lP9ncXWKnt-uNL59Eu_OboQ12glWqoYNjoAuXDmP4X5Uz3jnof" +
                    "9wyyNTcpkRhtXlPK0ZMnj5FtnLy29iiwqxHnCyYwaVxdCt4dB7lHYx9wNF23ZCWWTP1PC1uFUNKnqQ8y5UfAnAvUBlRqBomMuCSDP-1juqdr1-rOVpZSOVen" +
                    "XQP5cW4uYPnhr9HeoeqllyxQM4-B-UMKTnRjOwYS_m2oq68UNLXOARTQfPeUDvMVNiy17KBOUu-VuIUaIeTyRSZ-mccPOFKIPKF3MGdixj1cDr3z1-ASaGRjphf7NCq8z7-ZTS" +
                    "0j82P_05sTvlzu4X-3sHM7Rcr2XOHKJN3Rw5FH4nFpCxVXUZmUXcb-R-4B5XVGepAsNqWTabR7O71WPOeho6kKYu7SzPtxLZkBPO220sFlQAkDY7jNP_sncFssWDma9IDM"
    }

    @GET("api/get-all-services")
    fun getAllServices(): Call<AllServices>

    @POST("api/add-new-service")
    fun addNewService(
        @Body service: SendService,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "authorization" to userToken,
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<SendService>

    @DELETE("api/delete-service/{service_id}")
    fun deleteServiceById(
        @Path("service_id", encoded = true) id: Int,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "authorization" to userToken,
            "Accept" to "application/json"
        )
    ): Call<ResponseBody>


    @GET("api/get-service/{service_id}")
    fun getServiceById(
        @Path("service_id", encoded = true) id: Int,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
        )
    ): Call<ResponseModel<GetService>>

    @POST("api/update-service/{service_id}")
    fun updateService(
        @Path("service_id",encoded = true) id:Int,
        @Body service:SendService,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "authorization" to userToken,
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<SendService>

    @GET("api/get-all-category")
    fun getAllCategory(
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
        )
    )

}