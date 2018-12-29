package iniyan.com.swipetodeleteandeditrecyclerview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

public class SecondActivity extends AppCompatActivity {



    ArrayList<Item> items=new ArrayList<>();
DataAdapterDynamic adapterDynamic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

randamno();


        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        adapterDynamic = new DataAdapterDynamic(recyclerView,this,items);
     recyclerView.setAdapter(adapterDynamic);
     adapterDynamic.setLoadMore(new ILoadMore() {
         @Override
         public void onLoadMore() {

             if(items.size() <=20){

                 items.add(null);
                 adapterDynamic.notifyItemInserted(items.size()-1);
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
items.remove(items.size()-1);
adapterDynamic.notifyItemRemoved(items.size());


//Random  more data

                         int index= items.size();
                         int end =index+10;
                         for(int i=index;i<end;i++){

                             String name= UUID.randomUUID().toString();
                             Item item=new Item(name,name.length());
                             items.add(item);
                         }

                         adapterDynamic.notifyDataSetChanged();
                         adapterDynamic.setLoading();
                     }


                 },5000);
             }
             else {

                 Toast.makeText(getApplicationContext()," Load data Completed",Toast.LENGTH_SHORT).show();
             }



         }
     });
//        swipeRefreshLayout.setRefreshing(false);
//
//        adapter.notifyDataSetChanged();
    }

    private void randamno() {
        for(int i=0;i<40;i++){
            String name= UUID.randomUUID().toString();
            Item item=new Item(name,name.length());
            items.add(item);
        }
    }
}
