package com.example.webangleassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Tag;

public class MainActivity extends AppCompatActivity {
 public static String api="https://jsonplaceholder.typicode.com";
 List<usermodel> alluserlist;
 RecyclerView recy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recy=findViewById(R.id.recycle);


        recy.setLayoutManager(new LinearLayoutManager(this));


      retroinstance.getInstance().ainterface.getUsers().enqueue(new Callback<List<usermodel>>() {
          @Override
          public void onResponse(Call<List<usermodel>> call, Response<List<usermodel>> response) {
            alluserlist=response.body();

            recy.setAdapter(new useradapter(MainActivity.this , alluserlist));
            for(int i=0;i<alluserlist.size();i++){
                Log.e(api,"onresponse"+alluserlist.get(i).getTitle());

            }
          }


          @Override
          public void onFailure(Call<List<usermodel>> call, Throwable t) {
          Log.e("api","onfailure: "+t.getLocalizedMessage());
          }
      });


        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.usermodelmenu,menu);
        MenuItem SearchItem =menu.findItem(R.id.search);
        SearchView searchView=(SearchView) SearchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
             useradapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
