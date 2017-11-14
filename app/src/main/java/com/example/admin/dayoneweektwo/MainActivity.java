package com.example.admin.dayoneweektwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Greate";
    private EditText celebName;
    private EditText celebGender;
    private EditText celebYear;
    private TextView listOfCeleb;
    private EditText etDelete;
    private TextView etEditOld;
    private TextView etEditNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebName = findViewById(R.id.celebName);
        celebGender = findViewById(R.id.celebGender);
        listOfCeleb = findViewById(R.id.listOfCeleb);
        etDelete = findViewById(R.id.etDelete);
        etEditOld = findViewById(R.id.etEditOld);
        etEditNew = findViewById(R.id.etEditNew);
    }

    public void saveCeleb(View view) {
        DatabaseSqlLite databaseSqlLite = new DatabaseSqlLite(this);
        String name = celebName.getText().toString();
        String gender = celebGender.getText().toString();

        Celebrity celebrity = new Celebrity(name, gender);

        int getId = view.getId();

        switch (getId){
            case R.id.saveData:
                long row =databaseSqlLite.AddCelebrity(celebrity);
                String toasting;
                if(row>0){
                    toasting = "Saved";
                    Toast.makeText(this, toasting, Toast.LENGTH_SHORT).show();
                }else{
                    toasting = "Not save";
                    Toast.makeText(this, toasting, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.showData:
                listOfCeleb.setText(databaseSqlLite.getCeleb().toString());

                break;
            case R.id.deleteData:
                String nametoDelete = etDelete.getText().toString();
                Log.d(TAG, "saveCeleb: "+ nametoDelete);
               databaseSqlLite.deleteCeleb(nametoDelete);


                   toasting = "Done";
                   Toast.makeText(this, toasting, Toast.LENGTH_SHORT).show();

                break;
            case R.id.editData:
                String oldname = etEditOld.getText().toString();
                String newName = etEditNew.getText().toString();
                databaseSqlLite.updateCeleb(oldname, newName);
                toasting="Edit is done";
                Toast.makeText(this, toasting, Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
