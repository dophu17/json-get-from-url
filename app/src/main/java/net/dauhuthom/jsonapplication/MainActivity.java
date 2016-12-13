package net.dauhuthom.jsonapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

//    ArrayList<Product> list;
//    ProductAdapter adapter;
    ArrayList<String> list;

    ListView lvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new DocJSON().execute("http://khoapham.vn/KhoaPhamTraining/laptrinhios/jSON/demo3.json");
            }
        });

    }

    private void addControls() {
        lvProduct = (ListView) findViewById(R.id.lvProduct);
//        list = new ArrayList<>();
//        adapter = new ProductAdapter(this, list);
//        lvProduct.setAdapter(adapter);
    }

    class DocJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                list = new ArrayList<String>();

                JSONObject root = new JSONObject(s);
                JSONArray jsonArray = root.getJSONArray("danhsach");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject product = jsonArray.getJSONObject(i);
//                    int id = product.getInt("id");
                    String name = product.getString("khoahoc");
//                    Double price = product.getDouble("price");
                    list.add(name);
                }

                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                lvProduct.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    }

    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

}
