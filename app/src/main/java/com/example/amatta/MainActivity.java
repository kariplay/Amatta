package com.example.amatta;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity  {
    private Spinner first;
    ArrayAdapter<CharSequence> adspin1, adspin2; //어댑터를 선언 adspint1(서울,부산..) adspin2(강남구,강서구..)
    Button btpaired, btsend;
    ListView btlist;

    BluetoothAdapter btAdapter;
    private final static int REQUEST_ENABLE_BT = 1;

    Set<BluetoothDevice> pairedDevice;
    ArrayAdapter<String> btArrayAdapter;
    ArrayList<String> deviceAddressArray;
    BluetoothSocket btSocket = null;
    ConnectedThread connectedThread;

    String TAG = "MainActivity";
    UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    int x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btpaired = (Button) findViewById(R.id.BTpaired);
        btsend = (Button) findViewById(R.id.BTsend);

        btlist = (ListView) findViewById(R.id.BTlist);

        btlist.setOnItemClickListener(new myOnItemClickListener());

        final Spinner spin1 = (Spinner) findViewById(R.id.first);
        final Spinner spin2 = (Spinner) findViewById(R.id.second);

        adspin1 = ArrayAdapter.createFromResource(this, R.array.first_region, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);

        btArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        deviceAddressArray = new ArrayList<>();
        btlist.setAdapter(btArrayAdapter);

        String[] permission_list = {
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.BLUETOOTH_SCAN,
                android.Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.BLUETOOTH_CONNECT
        };

        ActivityCompat.requestPermissions(MainActivity.this, permission_list, 1);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!btAdapter.isEnabled()) {
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT);
        }

        Spinner first = (Spinner) findViewById(R.id.first);
        first.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if (adspin1.getItem(position).equals("서울특별시")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region1, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("종로구") || adspin2.getItem(i).equals("중구")) {
                                x = 60;
                                y = 127;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("용산구")) {
                                x = 60;
                                y = 126;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("성동구") || adspin2.getItem(i).equals("동대문구")) {
                                x = 61;
                                y = 127;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("광진구") || adspin2.getItem(i).equals("강동구") || adspin2.getItem(i).equals("송파구")) {
                                x = 62;
                                y = 126;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("중랑구")) {
                                x = 62;
                                y = 128;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("성북구")) {
                                x = 61;
                                y = 127;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("강북구")) {
                                x = 61;
                                y = 128;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("도봉구") || adspin2.getItem(i).equals("노원구")) {
                                x = 61;
                                y = 129;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("은평구") || adspin2.getItem(i).equals("서대문구") || adspin2.getItem(i).equals("마포구")) {
                                x = 59;
                                y = 127;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("양천구") || adspin2.getItem(i).equals("강서구") || adspin2.getItem(i).equals("영등포구")) {
                                x = 58;
                                y = 126;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("구로구")) {
                                x = 58;
                                y = 125;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("금천구")) {
                                x = 59;
                                y = 124;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("동작구") || adspin2.getItem(i).equals("관악구")) {
                                x = 59;
                                y = 125;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("서초구")) {
                                x = 61;
                                y = 125;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("강남구")) {
                                x = 61;
                                y = 126;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }