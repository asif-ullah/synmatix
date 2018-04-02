package au.com.synmatix;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by FahAd SheIkH on 9/12/2017.
 */

public interface APIServices_interface {

    @GET("synmatix_contact_us.php")
    Call<Result_quote> send_quote(@Query("user_name") String getuser_name, @Query("email_Address") String getEmailId
            , @Query("user_phone") String getPhone, @Query("message") String getmessage);
}
