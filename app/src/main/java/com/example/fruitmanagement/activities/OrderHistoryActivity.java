package com.example.fruitmanagement.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fruitmanagement.R;
import com.example.fruitmanagement.adapters.OrderAdapter;
import com.example.fruitmanagement.constants.Constants;
import com.example.fruitmanagement.daos.OrderDAO;
import com.example.fruitmanagement.DTO.OrderDTO;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {
    ListView listOrderHistoryView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        listOrderHistoryView = findViewById(R.id.listOrderHistoryView);

        OrderAdapter adapter = new OrderAdapter();
        OrderDAO dao = new OrderDAO(this);
        try {
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
            String userId = sharedPreferences.getString("IDPref", null);
            ArrayList<OrderDTO> orders = dao.getOrders(userId);
            adapter.setOrderDTOArrayList(orders);
            listOrderHistoryView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}