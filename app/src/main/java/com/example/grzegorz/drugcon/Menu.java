package com.example.grzegorz.drugcon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    ImageButton first;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        addListenerOnButton();
    }

    private void addListenerOnButton() {

        first = (ImageButton) findViewById(R.id.imageButton4);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Menu.this,"Po co w to klikasz?",Toast.LENGTH_SHORT).show();

                startList();

            }
        });
    }

    private void startList(){
        Intent list = new Intent(this,List.class);
        startActivity(list);
    }
}
