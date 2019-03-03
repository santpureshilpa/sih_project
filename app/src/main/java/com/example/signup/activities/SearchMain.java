package com.example.signup.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.signup.R;
import com.example.signup.utilities.AppPreference;

public class SearchMain extends AppCompatActivity {
   ListView list;
    String TYPE[] = {"Government Shop", "Private Shop", "Private Shop","Private Shop","Private Shop","Private Shop","Private Shop", "Private Shop","Private Shop"};
   String NAMES[] = {"MarketYard", "Mahalakshmi", "BigBazaar","Food For All","Reliance","SaiKrupa","Ganesh","Mango","Rstore"};
   String ADDRESS[] = {"Sector 3,ChandanNagar,Pune" ,"Baif Road,Wagholi,pune", "ABCD","Kesnand Phata,Wagholi","@post Salse","Swarget,pune","Laxmi Road,pune","Dhayari,sinhagad Road,pune","Nashik Phata,pune"};
   int imgs[] = {R.drawable.shops, R.drawable.shops, R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.shops};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
        list = findViewById(R.id.list1);
        MyAdapter adapter = new MyAdapter(this,TYPE, NAMES,ADDRESS,imgs);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String distributorId = "0sIkk3QsHRvgVyFc4KOv";
                AppPreference.setClickedDistributor(SearchMain.this, distributorId);
                if (position==0){
                    //Toast.makeText(MainActivity.this, "government shop", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SearchMain.this, Store1ProductList.class);
                    startActivity(intent);

                }
                if (position==1){
                   // Toast.makeText(MainActivity.this, "Private shop", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SearchMain.this, Store1ProductList.class);
                    startActivity(intent);
                }
                if (position==2){
                    //Toast.makeText(MainActivity.this, "Private shop", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SearchMain.this, Store1ProductList.class);
                    startActivity(intent);
                }

                if (position==3){
                    //Toast.makeText(MainActivity.this, "Private shop", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SearchMain.this, Store1ProductList.class);
                    startActivity(intent);
                }
                if (position==4){
                    //Toast.makeText(MainActivity.this, "Private shop", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SearchMain.this, Store1ProductList.class);
                    startActivity(intent);
                }
                if (position==5){
                    //Toast.makeText(MainActivity.this, "Private shop", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SearchMain.this, Store1ProductList.class);
                    startActivity(intent);
                }
                if (position==6){
                    //Toast.makeText(MainActivity.this, "Private shop", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SearchMain.this, Store1ProductList.class);
                    startActivity(intent);
                }
                if (position==7){
                    //Toast.makeText(MainActivity.this, "Private shop", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SearchMain.this, Store1ProductList.class);
                    startActivity(intent);
                }
                if (position==8){
                    //Toast.makeText(MainActivity.this, "Private shop", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SearchMain.this, Store1ProductList.class);
                    startActivity(intent);
                }

            }
        });

    }
    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String myTypes[];
        String myNames[];
        String myAddress[];
        int[] imgs;

        MyAdapter(Context c, String[] TYPE, String[] NAMES,String[] ADDRESS,int[] imgs){
            super(c,R.layout.row,R.id.text1,NAMES);
            this.context=c;
            this.imgs=imgs;
            this.myTypes=TYPE;
            this.myNames=NAMES;
            this.myAddress=ADDRESS;

        }


        @Override
        @NonNull
        public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.logo);
            TextView myName = row.findViewById(R.id.text1);
            TextView myADdress = row.findViewById(R.id.text2);
            TextView myTypes = row.findViewById(R.id.text3);
            images.setImageResource(imgs[position]);
            myName.setText(NAMES[position]);
            myADdress.setText(ADDRESS[position]);
            myTypes.setText(TYPE[position]);

            return row;
        }
    }
}
