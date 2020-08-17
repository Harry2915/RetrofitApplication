package harish.hibare.retrofitapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import harish.hibare.retrofitapplication.apiinterfaces.Apis;
import harish.hibare.retrofitapplication.pojojo.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText ename, edemail, edphone, edpassword, cpass;
    //TextView textView;
    String name, email, phone, password;
    Button button;
    Apis service;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Apis.base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(Apis.class);


        ename = findViewById(R.id.name);
        edemail = findViewById(R.id.email);
        edphone = findViewById(R.id.phone);
        edpassword = findViewById(R.id.pass);
        cpass = findViewById(R.id.cpass);
        button = findViewById(R.id.button);
        // textView=findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                name = ename.getText().toString();
                email = edemail.getText().toString();
                phone = edphone.getText().toString();
                password = edpassword.getText().toString();
                // textView.setText(" Name :  " + name + " Email : " + email + "  phone :"+phn);
                registerUser();
            }
        });

    }

    private void registerUser() {
        Call<User> call = service.registerUser(name, email, password, phone);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().matches("inserted")){
                    User user = response.body();
                    System.out.println(response.body());
                }else if(response.body().getResponse().matches("exists")){
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("User SignUp");
                    builder.setMessage("Email id Already Taken");
                    builder.setCancelable(true);
                    builder.show();
                    User user = response.body();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(""+t);
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


}