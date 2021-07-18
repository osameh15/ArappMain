package ir.arapp.arappmain.util.server

import ir.arapp.arappmain.model.base.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {
    companion object {
        val BaseUrl = "http://arrapp.herokuapp.com/"
        var userToken =
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiN2U3ZjNmMzVjNGEzOTAxZmY" +
                    "yNTc0Mjc1OTcwOWM3YWJlZGE1YTBmOGRjZmRmZDhhZTFkZjlmNGM2N2JmNjIwMjY5ZGU3ZWNlMGM3Y2Q5MjMiL" +
                    "CJpYXQiOjE2MjY2MzE5ODYuMTc4ODAyLCJuYmYiOjE2MjY2MzE5ODYuMTc4ODA4LCJleHAiOjE2NTgxNjc5ODYuMTcyN" +
                    "jc0LCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.m4FuIQWvAiEHzo-j4Jug7WNUQVij33wgVTdr76o00EC8CPvtugdEmuRWmEj1" +
                    "Gh4ETt5pFQG6neJWnWtU0_zalr7S-yIZ4N82YXlKL16XU3XNuVE9D49q0S-PFkBGckBks7xo0glotmh_lSGY2GN1jRLzXGVz_" +
                    "buTFCzTMiy9-cNGZWY6EOcikycuiLT5kn1vGWzpEcP1sudZZ1O8p6OrGjHDgXRX_vgTtH17Zlbp0MZn3tcauMGYXntcwRTd" +
                    "2KipvReuawJCZM-ZGPg7QCoQP23iU0LdWxMQOC0I04-gZSVYnaer4M4cA_7j91JwcwYYnH64OT6knwYxUnbqy9oMFfYGxhOETX" +
                    "jwjU8phKZV7cRpOWkNkOFprrItdi4furGNWnqHDUqdiddp-gNghrCuThGZPOiC5eJdAvlf1bzJT_oKfTSlVjmE7pPDkDxytq" +
                    "IrrlwIKT2B4UaO1g4ArJA4lPprNo4-qbIkm82TxRrrUG8YhphqIzeCWh7asXeIVdJJjnuZKbA5YZxNso-EnydxXI2koF9rSH6_RTQ7xAqU1AH0KAtka5nZ" +
                    "4D0kdiizBct0WkiQBLWAudf6YDATpgiYRV6KgDAEUmyPBOdJaaBZ2pNwLXAcN1syTWMOhf-TTRpnom4Q3w_ZSayhrvIJ6bcu3gd9nBXksKY_Jt16uBUd_Hk"
    }

    @GET("api/get-all-services")
    fun getAllServices(): Call<ResponseModel<GetAllServices>>

    @POST("api/add-new-service")
    fun addNewService(
        @Body serviceData: PostServiceData,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "authorization" to userToken,
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<PostServiceData>

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
        @Body serviceData:PostServiceData,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "authorization" to userToken,
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<PostServiceData>

    @GET("api/get-all-category")
    fun getAllCategory(
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
        )
    ):Call<ResponseModel<GetAllCategories>>

//  register api
    @POST("api/register")
    fun registerUser(
        @Body registerBody:RegisterBody,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ):Call<ResponseModel<RegisterResult>>
    @POST("api/resend")
    fun resendUser(
        @Body registerBody:RegisterResult,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ):Call<ResponseModel<RegisterResult>>

    @POST("api/verify")
    fun verifyUser(
        @Body verify:Verify,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )):Call<ResponseBody>

    @POST("api/set-user-information")
    fun setUserInfo(
        @Body userInfo: UserInfo,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ):Call<ResponseBody>


    @POST("api/login")
    fun loginUser(
        @Body loginMobile: LoginMobile,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ):Call<ResponseBody>


    @POST("api/forget-request")
    fun forgetRequest(
        @Body number: RegisterBody,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ):Call<ResponseBody>

    @POST("api/verify-forgot-password-code")
    fun verifyForgotPasswordCode(
        @Body verify: Verify,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ):Call<ResponseBody>

    @POST("api/set-new-password")
    fun setNewPassword(
        @Body newPassword: NewPassword,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ):Call<ResponseBody>




}