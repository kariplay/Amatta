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

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else if (adspin1.getItem(position).equals("부산광역시")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region2, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("중구") || adspin2.getItem(i).equals("서구")) {
                                x = 97;
                                y = 74;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("동구") || adspin2.getItem(i).equals("남구")) {
                                x = 98;
                                y = 75;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("영도구")) {
                                x = 98;
                                y = 74;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("부산진구")) {
                                x = 97;
                                y = 75;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("동래구") || adspin2.getItem(i).equals("연제구")) {
                                x = 98;
                                y = 76;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("북구") || adspin2.getItem(i).equals("강서구")) {
                                x = 96;
                                y = 76;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("해운대구") || adspin2.getItem(i).equals("수영구")) {
                                x = 99;
                                y = 75;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("사하구")) {
                                x = 96;
                                y = 74;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("금정구")) {
                                x = 98;
                                y = 77;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("사상구")) {
                                x = 96;
                                y = 75;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("기장군")) {
                                x = 100;
                                y = 77;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("대구광역시")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region3, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("중구") || adspin2.getItem(i).equals("남구") || adspin2.getItem(i).equals("수성구")) {
                                x = 89;
                                y = 90;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("동구")) {
                                x = 90;
                                y = 91;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("서구") || adspin2.getItem(i).equals("달서구")) {
                                x = 88;
                                y = 90;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("북구")) {
                                x = 89;
                                y = 91;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("달성군")) {
                                x = 86;
                                y = 88;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("인천광역시")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region4, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("중구") || adspin2.getItem(i).equals("동구")) {
                                x = 54;
                                y = 125;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("옹진군") || adspin2.getItem(i).equals("미추홀구")) {
                                x = 54;
                                y = 124;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("연수구")) {
                                x = 55;
                                y = 123;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("남동구")) {
                                x = 56;
                                y = 124;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("부평구")) {
                                x = 55;
                                y = 125;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("계양구")) {
                                x = 56;
                                y = 126;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("서구")) {
                                x = 55;
                                y = 126;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("강화군")) {
                                x = 51;
                                y = 130;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("광주광역시")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region5, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("동구")) {
                                x = 60;
                                y = 74;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("서구")) {
                                x = 59;
                                y = 74;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("남구")) {
                                x = 59;
                                y = 73;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("북구")) {
                                x = 59;
                                y = 75;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("광산구")) {
                                x = 57;
                                y = 74;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("대전광역시")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region6, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("서구")) {
                                x = 67;
                                y = 100;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("유성구")) {
                                x = 67;
                                y = 101;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("대덕구") || adspin2.getItem(i).equals("동구") || adspin2.getItem(i).equals("중구")) {
                                x = 68;
                                y = 100;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("울산광역시")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region7, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("중구") || adspin2.getItem(i).equals("남구")) {
                                x = 102;
                                y = 84;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("동구")) {
                                x = 104;
                                y = 83;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("북구")) {
                                x = 103;
                                y = 85;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("울주군")) {
                                x = 101;
                                y = 84;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("세종특별자치시")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region8, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("세종특별자치시")) {
                                x = 66;
                                y = 103;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("경기도")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region9, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("수원시장안구")) {
                                x = 60;
                                y = 121;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("수원시권선구")) {
                                x = 60;
                                y = 120;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("수원시팔달구")) {
                                x = 61;
                                y = 121;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("수원시영통구")) {
                                x = 61;
                                y = 120;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("성남시수정구") || adspin2.getItem(i).equals("성남시중원구")) {
                                x = 63;
                                y = 124;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("성남시분당구")) {
                                x = 62;
                                y = 123;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("의정부시")) {
                                x = 61;
                                y = 130;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("안양시만안구") || adspin2.getItem(i).equals("안양시동안구")) {
                                x = 59;
                                y = 123;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("부천시")) {
                                x = 56;
                                y = 125;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("광명시")) {
                                x = 58;
                                y = 125;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("평택시")) {
                                x = 62;
                                y = 114;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("동두천시")) {
                                x = 61;
                                y = 134;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("안산시상록구")) {
                                x = 58;
                                y = 121;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("안산시단원구")) {
                                x = 57;
                                y = 121;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("고양시덕양구")) {
                                x = 57;
                                y = 128;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("고양시일산동구") || adspin2.getItem(i).equals("고양시일산서구")) {
                                x = 56;
                                y = 129;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("과천시")) {
                                x = 60;
                                y = 124;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("구리시")) {
                                x = 62;
                                y = 127;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("남양주시")) {
                                x = 64;
                                y = 128;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("오산시")) {
                                x = 62;
                                y = 118;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("시흥시")) {
                                x = 57;
                                y = 123;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("군포시")) {
                                x = 59;
                                y = 122;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("의왕시")) {
                                x = 60;
                                y = 122;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("하남시")) {
                                x = 64;
                                y = 126;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("용인시처인구")) {
                                x = 64;
                                y = 119;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("용인시기흥구")) {
                                x = 62;
                                y = 120;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("용인시수지구")) {
                                x = 62;
                                y = 121;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("파주시")) {
                                x = 56;
                                y = 131;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("이천시")) {
                                x = 68;
                                y = 121;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("안성시")) {
                                x = 65;
                                y = 115;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("김포시")) {
                                x = 55;
                                y = 128;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("화성시")) {
                                x = 57;
                                y = 119;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("광주시")) {
                                x = 65;
                                y = 123;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("양주시")) {
                                x = 61;
                                y = 131;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("포천시")) {
                                x = 64;
                                y = 134;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("여주시")) {
                                x = 71;
                                y = 121;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("연천군")) {
                                x = 61;
                                y = 138;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("가평군")) {
                                x = 69;
                                y = 133;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("강원도")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region10, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("춘천시")) {
                                x = 73;
                                y = 134;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("원주시")) {
                                x = 76;
                                y = 122;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("강릉시")) {
                                x = 92;
                                y = 131;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("동해시")) {
                                x = 97;
                                y = 127;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("태백시")) {
                                x = 95;
                                y = 119;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("속초시")) {
                                x = 87;
                                y = 141;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("삼척시")) {
                                x = 98;
                                y = 125;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("홍천군")) {
                                x = 75;
                                y = 130;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("횡성군")) {
                                x = 77;
                                y = 125;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("영월군")) {
                                x = 86;
                                y = 119;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("평창군")) {
                                x = 84;
                                y = 123;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("정선군")) {
                                x = 89;
                                y = 123;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("철원군")) {
                                x = 65;
                                y = 139;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("화천군")) {
                                x = 72;
                                y = 139;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("양구군")) {
                                x = 77;
                                y = 139;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("인제군")) {
                                x = 80;
                                y = 138;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("고성군")) {
                                x = 85;
                                y = 145;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("양양군")) {
                                x = 88;
                                y = 138;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("충청북도")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region11, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("청주시상당구")) {
                                x = 69;
                                y = 106;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("청주시서원구")) {
                                x = 69;
                                y = 107;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("청주시흥덕구")) {
                                x = 67;
                                y = 106;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("청주시청원구")) {
                                x = 69;
                                y = 107;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("충주시")) {
                                x = 76;
                                y = 114;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("제천시")) {
                                x = 81;
                                y = 118;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("보은군")) {
                                x = 73;
                                y = 103;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("옥천군")) {
                                x = 71;
                                y = 99;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("영동군")) {
                                x = 74;
                                y = 97;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("증평군")) {
                                x = 71;
                                y = 110;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("진천군")) {
                                x = 68;
                                y = 111;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("괴산군")) {
                                x = 74;
                                y = 111;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("음성군")) {
                                x = 72;
                                y = 113;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("단양군")) {
                                x = 84;
                                y = 115;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("충청남도")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region12, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("천안시동남구")) {
                                x = 63;
                                y = 110;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("천안시서북구")) {
                                x = 63;
                                y = 112;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("공주시")) {
                                x = 63;
                                y = 102;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("보령시")) {
                                x = 54;
                                y = 100;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("아산시")) {
                                x = 60;
                                y = 110;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("서산시")) {
                                x = 51;
                                y = 110;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("논산시")) {
                                x = 62;
                                y = 97;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("계룡시")) {
                                x = 65;
                                y = 99;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("당진시")) {
                                x = 54;
                                y = 112;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("금산군")) {
                                x = 69;
                                y = 95;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("부여군")) {
                                x = 59;
                                y = 99;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("서천군")) {
                                x = 55;
                                y = 94;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("청양군")) {
                                x = 57;
                                y = 103;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("홍성군")) {
                                x = 55;
                                y = 106;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("예산군")) {
                                x = 58;
                                y = 107;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("태안군")) {
                                x = 48;
                                y = 109;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("전라북도")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region13, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("전주시완산구")) {
                                x = 63;
                                y = 89;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("전주시덕진구")) {
                                x = 63;
                                y = 89;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("군산시")) {
                                x = 56;
                                y = 92;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("익산시")) {
                                x = 60;
                                y = 91;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("정읍시")) {
                                x = 58;
                                y = 83;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("남원시")) {
                                x = 68;
                                y = 80;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("김제시")) {
                                x = 59;
                                y = 88;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("완주군")) {
                                x = 63;
                                y = 89;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("진안군")) {
                                x = 68;
                                y = 88;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("무주군")) {
                                x = 72;
                                y = 93;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("장수군")) {
                                x = 70;
                                y = 85;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("임실군")) {
                                x = 66;
                                y = 84;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("순창군")) {
                                x = 63;
                                y = 79;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("고창군")) {
                                x = 56;
                                y = 80;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("부안군")) {
                                x = 56;
                                y = 87;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("전라남도")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region14, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("목포시")) {
                                x = 50;
                                y = 67;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("여수시")) {
                                x = 73;
                                y = 66;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("순천시")) {
                                x = 70;
                                y = 70;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("나주시")) {
                                x = 56;
                                y = 71;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("광양시")) {
                                x = 73;
                                y = 70;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("담양군")) {
                                x = 61;
                                y = 78;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("곡성군")) {
                                x = 66;
                                y = 77;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("구례군")) {
                                x = 69;
                                y = 75;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("고흥군")) {
                                x = 66;
                                y = 62;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("보성군")) {
                                x = 62;
                                y = 66;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("화순군")) {
                                x = 61;
                                y = 72;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("장흥군")) {
                                x = 59;
                                y = 64;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("강진군")) {
                                x = 57;
                                y = 53;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("해남군")) {
                                x = 54;
                                y = 61;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("영암군")) {
                                x = 56;
                                y = 66;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("무안군")) {
                                x = 52;
                                y = 71;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("함평군")) {
                                x = 52;
                                y = 72;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("영광군")) {
                                x = 52;
                                y = 77;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("장성군")) {
                                x = 57;
                                y = 77;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("완도군")) {
                                x = 57;
                                y = 56;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("진도군")) {
                                x = 48;
                                y = 59;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("신안군")) {
                                x = 50;
                                y = 66;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("경상북도")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region15, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("포항시남구")) {
                                x = 102;
                                y = 94;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("포항시북구")) {
                                x = 102;
                                y = 95;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("경주시")) {
                                x = 100;
                                y = 91;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("김천시")) {
                                x = 80;
                                y = 96;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("안동시")) {
                                x = 91;
                                y = 106;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("구미시")) {
                                x = 84;
                                y = 96;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("영주시")) {
                                x = 89;
                                y = 111;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("영천시")) {
                                x = 95;
                                y = 93;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("상주시")) {
                                x = 81;
                                y = 102;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("문경시")) {
                                x = 81;
                                y = 106;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("경산시")) {
                                x = 91;
                                y = 90;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("군위군")) {
                                x = 88;
                                y = 99;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("의성군")) {
                                x = 90;
                                y = 101;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("청송군")) {
                                x = 96;
                                y = 103;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("영양군")) {
                                x = 97;
                                y = 108;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("영덕군")) {
                                x = 102;
                                y = 103;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("청도군")) {
                                x = 91;
                                y = 86;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("고령군")) {
                                x = 83;
                                y = 87;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("성주군")) {
                                x = 83;
                                y = 91;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("칠곡군")) {
                                x = 85;
                                y = 93;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("예천군")) {
                                x = 86;
                                y = 107;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("봉화군")) {
                                x = 90;
                                y = 113;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("울진군")) {
                                x = 102;
                                y = 115;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("울릉군")) {
                                x = 127;
                                y = 127;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("경상남도")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.second_region16, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("창원시 의창구")) {
                                x = 90;
                                y = 77;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("창원시 성산구")) {
                                x = 91;
                                y = 76;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("창원시 마산합포구")) {
                                x = 89;
                                y = 76;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("창원시 마산회원구")) {
                                x = 89;
                                y = 76;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("창원시 진해구")) {
                                x = 91;
                                y = 75;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("진주시")) {
                                x = 81;
                                y = 75;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("통영시")) {
                                x = 68;
                                y = 87;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("사천시")) {
                                x = 80;
                                y = 71;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("김해시")) {
                                x = 95;
                                y = 77;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("밀양시")) {
                                x = 92;
                                y = 83;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("거제시")) {
                                x = 90;
                                y = 69;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("양산시")) {
                                x = 97;
                                y = 79;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("의령군")) {
                                x = 83;
                                y = 78;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("함안군")) {
                                x = 86;
                                y = 77;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("창녕군")) {
                                x = 87;
                                y = 83;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("고성군")) {
                                x = 85;
                                y = 71;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("남해군")) {
                                x = 77;
                                y = 68;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("하동군")) {
                                x = 74;
                                y = 73;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("산청군")) {
                                x = 76;
                                y = 80;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("함양군")) {
                                x = 74;
                                y = 82;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("거창군")) {
                                x = 77;
                                y = 86;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("합천군")) {
                                x = 81;
                                y = 84;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(position).equals("제주특별자치도")) {

                    adspin2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.seocnd_region17, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adspin2.getItem(i).equals("제주시")) {
                                x = 53;
                                y = 38;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            } else if (adspin2.getItem(i).equals("서귀포시")) {
                                x = 52;
                                y = 33;
                                Toast.makeText(getApplicationContext(), x + "+" + y, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    ;