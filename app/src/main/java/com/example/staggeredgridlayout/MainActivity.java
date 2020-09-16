package com.example.staggeredgridlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);


        List<Data> dataList=new ArrayList<>();
        dataList.add(new Data(R.drawable.a1));
        dataList.add(new Data(R.drawable.a2));
        dataList.add(new Data(R.drawable.a3));
        dataList.add(new Data(R.drawable.a4));
        dataList.add(new Data(R.drawable.a5));
        dataList.add(new Data(R.drawable.a6));
        dataList.add(new Data(R.drawable.a7));
        dataList.add(new Data(R.drawable.a8));
        dataList.add(new Data(R.drawable.a9));
        dataList.add(new Data(R.drawable.ak));
        dataList.add(new Data(R.drawable.alumni));
        dataList.add(new Data(R.drawable.bg));
        dataList.add(new Data(R.drawable.budha));
        dataList.add(new Data(R.drawable.china));
        dataList.add(new Data(R.drawable.cute));
        dataList.add(new Data(R.drawable.f));
        dataList.add(new Data(R.drawable.jaipur));



        RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DataAdapter adapter=new DataAdapter(this,dataList);
        recyclerView.setAdapter(adapter);




    }
}