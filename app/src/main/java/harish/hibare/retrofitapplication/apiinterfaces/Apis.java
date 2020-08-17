package harish.hibare.retrofitapplication.apiinterfaces;

import harish.hibare.retrofitapplication.pojojo.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apis {
    String base_url = "http://harishhibare2915.epizy.com/";
   /* $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $phone = $_POST['phone'];*/

    @FormUrlEncoded
    @POST("register1.php")
    Call<User> registerUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone);
}
