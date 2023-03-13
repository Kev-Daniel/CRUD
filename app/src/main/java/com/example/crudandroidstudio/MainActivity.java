package com.example.crudandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView id =(TextView)findViewById(R.id.edittextid);
        TextView name =(TextView)findViewById(R.id.edittextname);
        TextView address=(TextView)findViewById(R.id.edittextaddress);
        Button btninsert = (Button)findViewById(R.id.btnadd);
        Button btnupdate = (Button)findViewById(R.id.btnupdate);
        Button btndelete = (Button)findViewById(R.id.btndelete);
        Button btnget = (Button)findViewById(R.id.btnget);


        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = connectionclass();
                try {
                    if(connection != null){
                        String sqlinsert = "Insert into BdCanciones values ('" + id.getText().toString() + "','" + name.getText().toString() + "','" + address.getText().toString() + "')";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
                    }
                }catch (Exception ex ){
                    Log.e("Error 123456", ex.getMessage());


                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = connectionclass();
                try {
                    if(connection != null){
                        String sqlinsert = "update BdCanciones set Nombre ='" + name.getText().toString() + "', Address ='" + address.getText().toString() + "'where ID = '" + id.getText().toString()+ "'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
                    }
                }catch (Exception ex ){
                    Log.e("Error 123456", ex.getMessage());


                }

            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = connectionclass();
                try {
                    if(connection != null){
                        String sqlinsert = "Delete BdCanciones where ID = '" + id.getText().toString()+ "'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
                    }
                }catch (Exception ex ){
                    Log.e("Error 123456", ex.getMessage());


                }

            }
        });

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = connectionclass();
                try {
                    if(connection != null){
                        String sqlinsert = "Select * from BdCanciones where ID = '" + id.getText().toString()+ "'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
                        while (rs.next()){
                            name.setText(rs.getString(2));
                            address.setText(rs.getString(3));

                        }
                    }
                }catch (Exception ex ){
                    Log.e("Error 123456", ex.getMessage());


                }

            }
        });

    }










    @SuppressLint("NewApi")
    public Connection connectionclass(){
        Connection con = null ;
        String ip = "10.0.2.2", port = "50702", username = "DanielPulupa", password ="admin789" , databasename = "CrudSqlAndroid";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";User=" + username + ";password=" + password + ";";
            con= DriverManager.getConnection(connectionUrl);

        }
        catch (Exception ex){
            Log.e( "Error eded",ex.getMessage());

        }
        return con ;

    }
}