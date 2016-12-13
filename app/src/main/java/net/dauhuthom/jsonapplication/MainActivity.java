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

    ArrayList<Product> list;
    ProductAdapter adapter;

    ListView lvProduct;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        new DocJSON().execute("http://dauhuthom.net/webservice/server.php?getProduct&format=json");
    }

    private void addControls() {
        lvProduct = (ListView) findViewById(R.id.lvProduct);
        textView = (TextView) findViewById(R.id.textView);
        list = new ArrayList<>();
        adapter = new ProductAdapter(this, list);
        lvProduct.setAdapter(adapter);
    }

    class DocJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return new ReaderJSON().readFromURL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject root = new JSONObject(s);
                JSONArray products = root.getJSONArray("products");
                for (int i = 0; i < products.length(); i++) {
                    JSONObject product = products.getJSONObject(i);
                    int id = product.getInt("id");
                    String name = product.getString("name");
                    Double price = product.getDouble("price");
                    list.add(new Product(id, name, price));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }
    }
}
