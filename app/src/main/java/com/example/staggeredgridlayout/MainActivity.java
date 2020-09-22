package com.example.staggeredgridlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Data> dataList;
    Data data;
    Context context=MainActivity.this;
    DataAdapter adapter;
    SimpleArcLoader simpleArcLoader;
    EditText text;
    String Url;
    TextView find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.inputtext);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        simpleArcLoader=findViewById(R.id.loader);
        find=findViewById(R.id.find);
        Url="https://api.unsplash.com/search/photos/?client_id=yznicwNXoZ322FbHeM_IOib-JG_Uu_AtRyz_oMwXOx4&query=dubai";
        fetctData(Url);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text.getText().toString().isEmpty())
                {
                    text.setError("Enter text");
                }
                else
                {
                    String t=text.getText().toString();
                    Url="https://api.unsplash.com/search/photos/?client_id=yznicwNXoZ322FbHeM_IOib-JG_Uu_AtRyz_oMwXOx4&query="+t;
                    fetctData(Url);
                }
            }
        });

    }
    public void fetctData(String urlData)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, urlData, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dataList=new ArrayList<>();
                simpleArcLoader.start();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        JSONObject jsonObject2=jsonObject1.getJSONObject("urls");
                        String regular=jsonObject2.getString("regular");
                        String small=jsonObject2.getString("small");
                        data=new Data(regular,small);
                        dataList.add(data);

                    }
                    simpleArcLoader.stop();
                    recyclerView.setVisibility(View.VISIBLE);
                    simpleArcLoader.setVisibility(View.GONE);
                    adapter=new DataAdapter(context,dataList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.addItemDecoration(new SpaceDecoration(2));

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
}