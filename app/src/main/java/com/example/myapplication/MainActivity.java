package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText product_label;
    private EditText product_price;
    private Button save_button;
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private ProductDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product_label = findViewById(R.id.productLabel);
        product_price = findViewById(R.id.product_price);
        save_button = findViewById(R.id.saveButton);
        recyclerViewProducts = findViewById(R.id.productsList);
        dbHelper = new ProductDbHelper(this);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(new ArrayList<Product>());
        recyclerViewProducts.setAdapter(productAdapter);

        loadProducts();

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label = product_label.getText().toString();
                String priceString = product_price.getText().toString();

                if(label.isEmpty() || priceString.isEmpty()){
                    Toast.makeText(MainActivity.this, "values required", Toast.LENGTH_SHORT).show();
                    return;
                }
                int pu = Integer.parseInt(priceString);
                dbHelper.insertArticle(label, pu);
                product_label.setText("");
                product_price.setText("");

                loadProducts();
            }
        });
    }

    private void loadProducts() {
        ArrayList<Product> products = (ArrayList<Product>) dbHelper.getAllArticles();
        productAdapter.updateArticles(products);
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
