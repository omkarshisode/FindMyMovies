package network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://imdb146.p.rapidapi.com"
    private const val API_KEY = "x-rapidapi-key"
    private const val API_KEY_VALUE = "4e8957a2aemshf206ed296e235fbp13e3b8jsn030ef85b9e25"
    private const val HOST_KEY = "x-rapidapi-host"
    private const val HOST_KEY_VALUE = "imdb146.p.rapidapi.com"

    private var retrofit: Retrofit.Builder = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private var httpClient = OkHttpClient.Builder()
    private var loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    fun <T> createService(serviceClass: Class<T>): T {
        // Add authorization to request passing the headers in key
        // Clear any existing interceptor
        httpClient.interceptors().clear()
        if (!httpClient.interceptors().contains(loggingInterceptor)) {
            httpClient.addInterceptor(loggingInterceptor)
        }
        httpClient.addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader(API_KEY, API_KEY_VALUE)
                .addHeader(HOST_KEY, HOST_KEY_VALUE)
                .build()
            return@addInterceptor chain.proceed(request)
        }
        retrofit.client(httpClient.build())
        return retrofit.build().create(serviceClass)
    }
}