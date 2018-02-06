package com.example.parksoyeon.gridlayout2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int NUM_OF_BUTTONS = 9;
    private TextView tv_score;
    private TextView tv_combo;
    private Button btn_exit;
    private Button[] Buttons;
    private GridLayout container;
    private int click_cnt;
    private int button_num;
    private int score = 0;
    private int combo = 0;

    public void Random_buttons() {
        int[] a;
        button_num = 1;
        click_cnt = 0;

        a = new int[NUM_OF_BUTTONS];
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
            int n = 0;
            Buttons[i].setBackgroundColor(Color.LTGRAY);
            while (true) {
                n = (int) ((Math.random() * NUM_OF_BUTTONS));
                if (a[n] != 1){
                    break;
                }
            }
            Buttons[i].setText("" + (n + 1));
            a[n] = 1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_combo = (TextView) findViewById(R.id.tv_combo);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        container = (GridLayout) findViewById(R.id.btn_container);

        Buttons = new Button[NUM_OF_BUTTONS];
        for(int i = 0; i < NUM_OF_BUTTONS; i++)
            Buttons[i] = new Button(this);


        Random_buttons();
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
            Buttons[i].setWidth(350);
            Buttons[i].setHeight(250);
            container.addView(Buttons[i]);

            Buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button btn = (Button)view;
                    final int num = Integer.parseInt(btn.getText().toString());
                    click_cnt++;
                    if (num == button_num) {
                        btn.setBackgroundColor(Color.GRAY);
                        button_num++;
                        score += 10;
                        tv_score.setText("SCORE: " + score);
                    }
                    else {
                        score -=50;
                        tv_score.setText("SCORE: "+ score);
                    }
                    if(button_num == 10) {
                        if (click_cnt == 9) {
                            combo++;
                            tv_combo.setText("COMBO: "+combo);
                        }
                        Random_buttons();
                    }
                }
            });
            btn_exit.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    finish();
                }
            });
        }
    }
}
