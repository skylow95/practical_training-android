package com.example.jordan.apitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jordan.apitest.api.GitApi;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callback<Response> {

    @Bind(R.id.button)
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        click.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String user = "jordan1997";

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Values.HOST).build();

        GitApi git = restAdapter.create(GitApi.class);
        git.myPost(user, this);

    }

    @Override
    public void success(Response response, Response response2) {
        Log.d("deb", "success " + response.toString());
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("deb", "failure " + error.getMessage());
    }
}
