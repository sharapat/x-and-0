package com.example.qareken.lesson8;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by QAREKEN on 02.06.2017.
 */

public class SecondGame extends AppCompatActivity {
    int cnt, step = 0, time = 0;
    Button[][] board = new Button[6][6];
    Point[] points;
    boolean playing;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_game);
        points = new Point[15];
        playing = true;
        board[1][1] = (Button)findViewById(R.id.number1);
        board[1][2] = (Button)findViewById(R.id.number2);
        board[1][3] = (Button)findViewById(R.id.number3);
        board[1][4] = (Button)findViewById(R.id.number4);
        board[2][1] = (Button)findViewById(R.id.number5);
        board[2][2] = (Button)findViewById(R.id.number6);
        board[2][3] = (Button)findViewById(R.id.number7);
        board[2][4] = (Button)findViewById(R.id.number8);
        board[3][1] = (Button)findViewById(R.id.number9);
        board[3][2] = (Button)findViewById(R.id.number10);
        board[3][3] = (Button)findViewById(R.id.number11);
        board[3][4] = (Button)findViewById(R.id.number12);
        board[4][1] = (Button)findViewById(R.id.number13);
        board[4][2] = (Button)findViewById(R.id.number14);
        board[4][3] = (Button)findViewById(R.id.number15);
        board[4][4] = (Button)findViewById(R.id.number0);

        for (int i = 1; i <= 4 ; i++){
            for(int j = 1; j <= 4; j++){
                board[i][j].setTag(new Point(i, j));
            }
        }

        int[][] a = new int[4][4];
        int res = 1;
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j ++) {
                a[i][j] = res ++;
            }
        }


        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j ++) {
                Random r = new Random();
                int x = r.nextInt(4);
                int y = r.nextInt(4);
                int tmp = a[i][j];
                a[i][j] = a[x][y];
                a[x][y] = tmp;
            }
        }

        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j ++) {
                if(a[i][j] == 16){
                    board[i+1][j+1].setText("");
                    board[i+1][j+1].setBackground(getResources().getDrawable(R.drawable.white_color));
                }
                else {
                    board[i + 1][j + 1].setText("" + a[i][j]);
                    board[i + 1][j + 1].setBackground(getResources().getDrawable(R.drawable.enabled_button));
                }
            }
        }

        /*new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        }.start();*/
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                time++;
                                TextView timer = (TextView)findViewById(R.id.time);
                                if(playing == true)timer.setText("Vaqt: " + time/60 + ":" + time%60);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    public void NewGame(View view){
        points = new Point[15];
        playing = true;
        step = 0;
        time = 0;
        TextView timer = (TextView)findViewById(R.id.time);
        timer.setText("Vaqt: 0:0");
        TextView textView = (TextView)findViewById(R.id.step);
        textView.setText("Qadam: 0");
        for (int i = 1; i <= 4 ; i++){
            for(int j = 1; j <= 4; j++){
                board[i][j].setTag(new Point(i, j));
            }
        }

        int[][] a = new int[4][4];
        int res = 1;
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j ++) {
                a[i][j] = res ++;
            }
        }

        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j ++) {
                Random r = new Random();
                int x = r.nextInt(4);
                int y = r.nextInt(4);
                int tmp = a[i][j];
                a[i][j] = a[x][y];
                a[x][y] = tmp;
            }
        }

        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j ++) {
                if(a[i][j] == 16){
                    board[i+1][j+1].setText("");
                    board[i+1][j+1].setBackground(getResources().getDrawable(R.drawable.white_color));
                }
                else {
                    board[i + 1][j + 1].setText("" + a[i][j]);
                    board[i + 1][j + 1].setBackground(getResources().getDrawable(R.drawable.enabled_button));
                }
            }
        }
    }
    public void ImgClick(View view) {
        Button button = (Button)view;
        Point p = (Point) button.getTag();
        int first = p.x;
        int second = p.y;
        TextView textView = (TextView)findViewById(R.id.step);
        if(first > 1 && board[first-1][second].getText() == ""){
            String text;
            text = (String) board[first][second].getText();
            board[first][second].setText("");
            board[first][second].setBackground(getResources().getDrawable(R.drawable.white_color));
            board[first-1][second].setText(text);
            board[first-1][second].setBackground(getResources().getDrawable(R.drawable.enabled_button));
            step++;
            textView.setText("Qadam: " + step);
        }
        else if(first < 4 && board[first+1][second].getText() == ""){
            String text;
            text = (String) board[first][second].getText();
            board[first][second].setText("");
            board[first][second].setBackground(getResources().getDrawable(R.drawable.white_color));
            board[first+1][second].setText(text);
            board[first+1][second].setBackground(getResources().getDrawable(R.drawable.enabled_button));
            step++;
            textView.setText("Qadam: " + step);
        }
        else if(second > 1 && board[first][second-1].getText() == ""){
            String text;
            text = (String) board[first][second].getText();
            board[first][second].setText("");
            board[first][second].setBackground(getResources().getDrawable(R.drawable.white_color));
            board[first][second-1].setText(text);
            board[first][second-1].setBackground(getResources().getDrawable(R.drawable.enabled_button));
            step++;
            textView.setText("Qadam: " + step);
        }
        else if(second < 4 && board[first][second+1].getText() == ""){
            String text;
            text = (String) board[first][second].getText();
            board[first][second].setText("");
            board[first][second].setBackground(getResources().getDrawable(R.drawable.white_color));
            board[first][second+1].setText(text);
            board[first][second+1].setBackground(getResources().getDrawable(R.drawable.enabled_button));
            step++;
            textView.setText("Qadam: " + step);
        }
        cnt = 0;
        for(int i = 1; i <= 4; i ++){
            for(int j = 1; j <= 4; j++){
                Point point = (Point)board[i][j].getTag();
                int x = point.x;
                int y = point.y;
                int son = (x-1)*4+y;
                String t = "" + son;
                if(board[i][j].getText().toString().equals(t)) {
                    cnt++;
                }
            }
        }
        if(cnt == 15) {
            Toast.makeText(getApplicationContext(), "You Won!!!", Toast.LENGTH_SHORT).show();
            playing = false;
        }
    }
}
