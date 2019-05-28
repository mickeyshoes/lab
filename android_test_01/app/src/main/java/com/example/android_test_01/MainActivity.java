package com.example.android_test_01;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView txtmsg01;
    private String seturl = "http://10.0.2.2:8000/print/print_hello";
    Handler handler = new Handler();
    Async_test at;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtmsg01 = (TextView) findViewById(R.id.textview01);

        Button button2 = (Button) findViewById(R.id.test_start);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ConnectThread ct = new ConnectThread(seturl);
                ct.start();
            }

        });

        Button button1 = (Button)findViewById(R.id.reset_start);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                txtmsg01.setText("");
            }
        });

        Button button3 = (Button)findViewById(R.id.Astnc_start);
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                at = new Async_test();
                at.execute(seturl);

            }
        });

        Button button4 = (Button)findViewById(R.id.json_start);
        button4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"종료됩니다.", Toast.LENGTH_SHORT).show();
        at.cancel(true); // 스레드 종료
        super.onBackPressed();
    }

    class ConnectThread extends Thread{
        String seturl;

        public ConnectThread(String in){
            seturl = in;
        }

        public void run(){

            try {
                final String output = request(seturl);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txtmsg01.setText(output);
                    }
                });

            }catch(Exception e){
                e.printStackTrace();
            }
        }


        private String request(String seturl) {
            StringBuilder output = new StringBuilder(); // 가변형 String 문자열의 변경이 많은 경우 사용하면 좋음 싱글스레드 환경에서 속도가 buffer보다 빠름
            try{
                URL url = new URL(seturl);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();

                if(con != null){
                    con.setConnectTimeout(10000); // 연결대기시간 설정
                    con.setRequestMethod("GET");
                    // 입출력이 가능하게 해줌
                    con.setDoInput(true);
                    con.setDoOutput(true);

                    int reqcode = con.getResponseCode(); // 성공시 200 실패시 404,403 등등
                    Log.d("인터넷 연결은 되신겁니까?", String.valueOf(reqcode));

                    InputStreamReader isr = new InputStreamReader(con.getInputStream());
                    BufferedReader reader = new BufferedReader(isr);
                    String line = null;

                    while(true){
                        line = reader.readLine();
                        if(line == null){
                            break;
                        }
                        output.append(line + "\n");
                    }

                    reader.close();
                    con.disconnect();
                }

            }catch(SocketTimeoutException e2){
                //Toast.makeText(getApplicationContext(),"서버와 연결이 되지 않습니다.", Toast.LENGTH_LONG).show();
                Log.e("SampleHTTP", "한글따리 한글따.", e2);
            }

            catch(Exception e){
                Log.e("SampleHTTP", "Exception in processing response.", e);
                e.printStackTrace();
            }
            return output.toString();
        }

    }


    // <시작파라미터, 진행상태, 서버로 받은 데이터를 리턴할때 사용하는 타입>
    class Async_test extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... parameters) {
            Log.e("입력한 url이 이쪽으로 넘어오는가 확인",parameters[0]+"가 출력되었다.");
            StringBuilder output = new StringBuilder();

            try {
                URL url = new URL(parameters[0]);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();

                if(con!=null){
                    con.setConnectTimeout(10000);
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Accept-Charset", "UTF-8");
                    con.setDoInput(true);
                    con.setDoOutput(true);

                    int reqcode = con.getResponseCode();
                    Log.e("internet","인터넷 연결이 되었는지 확인");

                    InputStreamReader isr = new InputStreamReader(con.getInputStream());
                    BufferedReader reader = new BufferedReader(isr);
                    String line = null;

                    while(true){
                        line = reader.readLine();
                        if(line==null){
                            break;
                        }
                        output.append(line+"다읽었음");
                    }

                    reader.close();
                    con.disconnect();
                }
            } catch (MalformedURLException e) {
                Log.e("url","url이 잘못되었음. 입력받은 url = "+ parameters[0]);
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("url-connetcion","연결에 실패하였음. 입력받은 url = "+ parameters[0]);
                e.printStackTrace();
            }


            return output.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtmsg01.setText(s);
        }
    }



}
