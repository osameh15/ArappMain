package ir.arapp.arappmain.util.server

import ir.arapp.arappmain.model.base.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Api {
    companion object {
        val BaseUrl = "http://arrapp.herokuapp.com/"
        var userToken: String = ""
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
        @Path("service_id", encoded = true) id: Int,
        @Body serviceData: PostServiceData,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "authorization" to userToken,
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<PostServiceData>

    @GET("api/get-category-service/{category_id}")
    fun getServicesByCategoryId(
        @Path("category_id", encoded = true) categoryId: Int,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "authorization" to userToken,
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<ResponseModel<GetAllServices>>

    @GET("api/get-my-services")
    fun getMyServices(
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "authorization" to userToken,
            "accept" to "application/json"
        )
    ):Call<ResponseModel<GetAllServices>>


    @GET("api/get-all-category")
    fun getAllCategory(
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
        )
    ): Call<ResponseModel<GetAllCategories>>

    //  register api
    @POST("api/register")
    fun registerUser(
        @Body registerBody: RegisterBody,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<ResponseModel<RegisterResult>>

    @POST("api/resend")
    fun resendUser(
        @Body registerBody: RegisterResult,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<ResponseModel<RegisterResult>>

    @POST("api/verify")
    fun verifyUser(
        @Body verify: Verify,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<ResponseModel<GetToken>>

    @POST("api/set-user-information")
    fun setUserInfo(
        @Body userInfo: UserInfo,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<ResponseModel<LoginToken>>


    @POST("api/login")
    fun loginUser(
        @Body loginMobile: LoginMobile,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<ResponseModel<List<LoginToken>>>


    @POST("api/forget-request")
    fun forgetRequest(
        @Body number: RegisterBody,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<ResponseBody>

    @POST("api/verify-forgot-password-code")
    fun verifyForgotPasswordCode(
        @Body verify: Verify,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<ResponseBody>

    @POST("api/set-new-password")
    fun setNewPassword(
        @Body newPassword: NewPassword,
        @HeaderMap tokenMap: Map<String, String> = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json"
        )
    ): Call<ResponseBody>


}