package com.example.valverde.hearthstonecardsapp.api.remote;

import com.example.valverde.hearthstonecardsapp.api.model.Card;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface HearthstoneAPI {
    String BASE_URL = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/";
    String KEY_NAME = "X-Mashape-Key";
    String KEY = "WeTBi4gOtGmshPBkBtiwunde3CbWp1yXMBkjsnVH1zInnIOe0W";

    @Headers(KEY_NAME+": "+KEY)
    @GET("sets/Whispers of the Old Gods/")
    Call<List<Card>> getAllCards(@QueryMap Map<String, String> options);

    @Headers(KEY_NAME+": "+KEY)
    @GET("{name}/")
    Call<List<Card>> getCardByName(@Path("name") String name, @QueryMap Map<String, String> options);

    @Headers(KEY_NAME+": "+KEY)
    @GET("classes/{className}/")
    Call<List<Card>> getCardByClass(@Path("className") String className, @QueryMap Map<String, String> options);

    @Headers(KEY_NAME+": "+KEY)
    @GET("types/{type}/")
    Call<List<Card>> getCardByType(@Path("type") String type, @QueryMap Map<String, String> options);


    class RetrofitFactory {
        private static volatile HearthstoneAPI instance = null;

        public static HearthstoneAPI getInstance() {
            if (instance == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                instance = retrofit.create(HearthstoneAPI.class);
            }
            return instance;
        }
    }
}