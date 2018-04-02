package au.com.synmatix.Quote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import au.com.synmatix.APIServices_interface;
import au.com.synmatix.R;
import au.com.synmatix.Result_quote;
import au.com.synmatix.home_folder.Home;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static au.com.synmatix.Base_URL.BASE_URL;

/**
 * Created by user on 3/27/18.
 */

public class Quote extends Fragment {
    EditText name, email, phone, message;

    // object for library
    private static Retrofit retrofit = null;
    Button btn_submit;

    public Quote() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.quote_activity, container, false);
        name = (EditText) view.findViewById(R.id.name);
        email = (EditText) view.findViewById(R.id.emal);
        phone = (EditText) view.findViewById(R.id.phone);
        message = (EditText) view.findViewById(R.id.message);
        btn_submit = (Button) view.findViewById(R.id.submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRetrofitResult();


            }
        });


        return view;
    }

    private void getRetrofitResult() {
        String getuser_name = name.getText().toString();
        String getEmailId = email.getText().toString();
        String getPhone = phone.getText().toString();
        String getmessage = message.getText().toString();
        Pattern p = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b");
        Matcher m = p.matcher(getEmailId);
        // Check if all strings are null or not
        if (getuser_name.trim().equals("") || getuser_name.length() == 0)
            name.setError("enter name");
        else if (getPhone.trim().equals("") || getPhone.length() == 0)
            phone.setError("enter phone");
        else if (getmessage.trim().equals("") || getmessage.length() == 0)
            message.setError("enter message");


            // Check if email id valid or not
        else if (!m.find()) {
            email.setError("Invalid Email");
        } else {


            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            //Call to main class to get result from Api interface
            APIServices_interface myAPIServices = retrofit.create(APIServices_interface.class);
            Call<Result_quote> call = null;
            try {
                call = myAPIServices.send_quote(getuser_name, getEmailId, getPhone, getmessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (call != null) {
                try {
                    //check if object call receive data
                    call.enqueue(new Callback<Result_quote>() {
                        @Override
                        public void onResponse(Call<Result_quote> call, Response<Result_quote> response) {
                            Result_quote send = response.body();
                            String message = send.getMessage();

                            if (message.equalsIgnoreCase("success")) {
                                Toast.makeText(getContext(), "Email send succesfully", Toast.LENGTH_SHORT).show();

                                Home home = new Home();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.Fragment_Container, home);
                                fragmentTransaction.commit();

                            } else {
                                Toast.makeText(getContext(), "Try Again", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<Result_quote> call, Throwable t) {
                            Toast.makeText(getContext(), "Email not Send", Toast.LENGTH_SHORT).show();
                        }


                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }
}


