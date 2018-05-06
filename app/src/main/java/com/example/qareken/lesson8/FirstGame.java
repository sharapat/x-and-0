package com.example.qareken.lesson8;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by QAREKEN on 05.06.2017.
 */

public class FirstGame extends AppCompatActivity {
    ImageView[][] board = new ImageView[5][5];
    int indexi = 1, indexj = 1, cnt = 0, gamerWin = 0, computerWin = 0;
    int[][] a = new int[4][4];
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_game);
        board[1][1] = (ImageView)findViewById(R.id.button1);
        board[1][2] = (ImageView)findViewById(R.id.button2);
        board[1][3] = (ImageView)findViewById(R.id.button3);
        board[2][1] = (ImageView)findViewById(R.id.button4);
        board[2][2] = (ImageView)findViewById(R.id.button5);
        board[2][3] = (ImageView)findViewById(R.id.button6);
        board[3][1] = (ImageView)findViewById(R.id.button7);
        board[3][2] = (ImageView)findViewById(R.id.button8);
        board[3][3] = (ImageView)findViewById(R.id.button9);

        int res = 1;
        for (int i = 1; i <= 3; i ++) {
            for (int j = 1; j <= 3; j ++) {
                a[i][j] = res ++;
            }
        }
        Random();

    }
    public void Random(){
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                board[i][j].setTag(new MyType("*", i, j));
                Random r = new Random();
                int x = r.nextInt(2)+1;
                int y = r.nextInt(2)+1;
                int tmp = a[i][j];
                a[i][j] = a[x][y];
                a[x][y] = tmp;
            }
        }
    }
    public void Win(boolean win, boolean durrang){
        LinearLayout layout = (LinearLayout)findViewById(R.id.status_layout);
        TextView text = (TextView)findViewById(R.id.status);
        String info = "";
        if(durrang == true) {
            text.setText("Durrang");
            layout.setVisibility(View.VISIBLE);
            return ;
        }

        if(win == true){
            text.setText("G'alaba");
            gamerWin++;
        }
        else {

            text.setText("Mag'lubiyat");
            computerWin++;
        }
        TextView t = (TextView)findViewById(R.id.gamerWin);
        t.setText(gamerWin + ":" + computerWin);
       // Toast.makeText(this, "alom", Toast.LENGTH_SHORT).show();
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//              e.printStackTrace();
//        }
        layout.setVisibility(View.VISIBLE);
    }


    public void OnClick(View view){
        ImageView button = (ImageView)view;
        MyType type = (MyType) button.getTag();
        //Toast.makeText(getApplicationContext(), "" + type.x, Toast.LENGTH_SHORT).show();
        if(type.x.equals("0") || type.x.equals("x")){
            return ;
        }
        button.setImageDrawable(getResources().getDrawable(R.drawable.krestik));
        int x = type.i;
        int y = type.j;
        button.setTag(new MyType("x", x, y));
        cnt++;
        //CHECK FOR WIN
        for (int i = 1; i <= 3; i++){
            if((((MyType)board[i][1].getTag()).x).equals("x") && (((MyType)board[i][2].getTag()).x).equals("x") &&
                    (((MyType)board[i][3].getTag()).x).equals("x")){
                Win(true, false);
                return ;
            }

            if((((MyType)board[1][i].getTag()).x).equals("x") && (((MyType)board[2][i].getTag()).x).equals("x") &&
                    (((MyType)board[3][i].getTag()).x).equals("x")){
                Win(true, false);
                return ;
            }
        }
        if((((MyType)board[1][1].getTag()).x).equals("x") && (((MyType)board[2][2].getTag()).x).equals("x") &&
                (((MyType)board[3][3].getTag()).x).equals("x")){
            Win(true, false);
            return ;
        }

        if((((MyType)board[1][3].getTag()).x).equals("x") && (((MyType)board[2][2].getTag()).x).equals("x") &&
                (((MyType)board[3][1].getTag()).x).equals("x")){
            Win(true, false);
            return ;
        }

        //KOMPYUTER YURISHI

        if(cnt == 9){
            // Toast.makeText(getApplicationContext(), "Dostlik g'alaba qazondi", Toast.LENGTH_SHORT).show();
            Win(true, true);
            return ;
        }

        //AGAR KOMPYUTER YUTMOQCHI BOLSA

        cnt++;

        for (int i = 1; i <= 3; i++){
            if((((MyType)board[i][1].getTag()).x).equals("0") && (((MyType)board[i][2].getTag()).x).equals("0") &&
                    (((MyType)board[i][3].getTag()).x).equals("*")){

                board[i][3].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[i][3].setTag(new MyType("0", i, 3));

                Win(false, false);
                return ;
            }

            if((((MyType)board[i][1].getTag()).x).equals("0") && (((MyType)board[i][2].getTag()).x).equals("*") &&
                    (((MyType)board[i][3].getTag()).x).equals("0")){

                board[i][2].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[i][2].setTag(new MyType("0", i, 2));
                Win(false, false);
                return ;
            }

            if((((MyType)board[i][1].getTag()).x).equals("*") && (((MyType)board[i][2].getTag()).x).equals("0") &&
                    (((MyType)board[i][3].getTag()).x).equals("0")){

                board[i][1].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[i][1].setTag(new MyType("0", i, 1));
                Win(false, false);
                return ;
            }

            if((((MyType)board[1][i].getTag()).x).equals("0") && (((MyType)board[2][i].getTag()).x).equals("0") &&
                    (((MyType)board[3][i].getTag()).x).equals("*")){
                board[3][i].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[3][i].setTag(new MyType("0", 3, i));
                Win(false, false);
                return ;
            }

            if((((MyType)board[1][i].getTag()).x).equals("0") && (((MyType)board[2][i].getTag()).x).equals("*") &&
                    (((MyType)board[3][i].getTag()).x).equals("0")){
                board[2][i].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[2][i].setTag(new MyType("0", 2, i));
                Win(false, false);
                return ;
            }

            if((((MyType)board[1][i].getTag()).x).equals("*") && (((MyType)board[2][i].getTag()).x).equals("0") &&
                    (((MyType)board[3][i].getTag()).x).equals("0")){
                board[1][i].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[1][i].setTag(new MyType("0", 1, i));
                Win(false, false);
                return ;
            }
        }
        if((((MyType)board[1][1].getTag()).x).equals("0") && (((MyType)board[2][2].getTag()).x).equals("0") &&
                (((MyType)board[3][3].getTag()).x).equals("*")){
            board[3][3].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[3][3].setTag(new MyType("0", 3, 3));
            Win(false, false);
            return ;
        }

        if((((MyType)board[1][1].getTag()).x).equals("0") && (((MyType)board[2][2].getTag()).x).equals("*") &&
                (((MyType)board[3][3].getTag()).x).equals("0")){
            board[2][2].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[2][2].setTag(new MyType("0", 2, 2));
            Win(false, false);
            return ;
        }

        if((((MyType)board[1][1].getTag()).x).equals("*") && (((MyType)board[2][2].getTag()).x).equals("0") &&
                (((MyType)board[3][3].getTag()).x).equals("0")){
            board[1][1].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[1][1].setTag(new MyType("0", 1, 1));
            Win(false, false);
            return ;
        }

        if((((MyType)board[1][3].getTag()).x).equals("0") && (((MyType)board[2][2].getTag()).x).equals("0") &&
                (((MyType)board[3][1].getTag()).x).equals("*")){
            board[3][1].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[3][1].setTag(new MyType("0", 3, 1));
            Win(false, false);
            return ;
        }

        if((((MyType)board[1][3].getTag()).x).equals("0") && (((MyType)board[2][2].getTag()).x).equals("*") &&
                (((MyType)board[3][1].getTag()).x).equals("0")){
            board[2][2].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[2][2].setTag(new MyType("0", 2, 2));
            Win(false, false);
            return ;
        }

        if((((MyType)board[1][3].getTag()).x).equals("*") && (((MyType)board[2][2].getTag()).x).equals("0") &&
                (((MyType)board[3][1].getTag()).x).equals("0")){
            board[1][3].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[1][3].setTag(new MyType("0", 1, 3));
            Win(false, false);
            return ;
        }

        //AGAR OYINCHI YUTMOQCHI BOLSA

        for (int i = 1; i <= 3; i++){
            if((((MyType)board[i][1].getTag()).x).equals("x") && (((MyType)board[i][2].getTag()).x).equals("x") &&
                    (((MyType)board[i][3].getTag()).x).equals("*")){

                board[i][3].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[i][3].setTag(new MyType("0", i, 3));
                return ;
            }

            if((((MyType)board[i][1].getTag()).x).equals("x") && (((MyType)board[i][2].getTag()).x).equals("*") &&
                    (((MyType)board[i][3].getTag()).x).equals("x")){

                board[i][2].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[i][2].setTag(new MyType("0", i, 2));
                return ;
            }

            if((((MyType)board[i][1].getTag()).x).equals("*") && (((MyType)board[i][2].getTag()).x).equals("x") &&
                    (((MyType)board[i][3].getTag()).x).equals("x")){

                board[i][1].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[i][1].setTag(new MyType("0", i, 1));
                return ;
            }

            if((((MyType)board[1][i].getTag()).x).equals("x") && (((MyType)board[2][i].getTag()).x).equals("x") &&
                    (((MyType)board[3][i].getTag()).x).equals("*")){
                board[3][i].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[3][i].setTag(new MyType("0", 3, i));
                return ;
            }

            if((((MyType)board[1][i].getTag()).x).equals("x") && (((MyType)board[2][i].getTag()).x).equals("*") &&
                    (((MyType)board[3][i].getTag()).x).equals("x")){
                board[2][i].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[2][i].setTag(new MyType("0", 2, i));
                return ;
            }

            if((((MyType)board[1][i].getTag()).x).equals("*") && (((MyType)board[2][i].getTag()).x).equals("x") &&
                    (((MyType)board[3][i].getTag()).x).equals("x")){
                board[1][i].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                board[1][i].setTag(new MyType("0", 1, i));
                return ;
            }
        }
        if((((MyType)board[1][1].getTag()).x).equals("x") && (((MyType)board[2][2].getTag()).x).equals("x") &&
                (((MyType)board[3][3].getTag()).x).equals("*")){
            board[3][3].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[3][3].setTag(new MyType("0", 3, 3));
            return ;
        }

        if((((MyType)board[1][1].getTag()).x).equals("x") && (((MyType)board[2][2].getTag()).x).equals("*") &&
                (((MyType)board[3][3].getTag()).x).equals("x")){
            board[2][2].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[2][2].setTag(new MyType("0", 2, 2));
            return ;
        }

        if((((MyType)board[1][1].getTag()).x).equals("*") && (((MyType)board[2][2].getTag()).x).equals("x") &&
                (((MyType)board[3][3].getTag()).x).equals("x")){
            board[1][1].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[1][1].setTag(new MyType("0", 1, 1));
            return ;
        }

        if((((MyType)board[1][3].getTag()).x).equals("x") && (((MyType)board[2][2].getTag()).x).equals("x") &&
                (((MyType)board[3][1].getTag()).x).equals("*")){
            board[3][1].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[3][1].setTag(new MyType("0", 3, 1));
            return ;
        }

        if((((MyType)board[1][3].getTag()).x).equals("x") && (((MyType)board[2][2].getTag()).x).equals("*") &&
                (((MyType)board[3][1].getTag()).x).equals("x")){
            board[2][2].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[2][2].setTag(new MyType("0", 2, 2));
            return ;
        }

        if((((MyType)board[1][3].getTag()).x).equals("*") && (((MyType)board[2][2].getTag()).x).equals("x") &&
                (((MyType)board[3][1].getTag()).x).equals("x")){
            board[1][3].setImageDrawable(getResources().getDrawable(R.drawable.nol));
            board[1][3].setTag(new MyType("0", 1, 3));
            return ;
        }

        //AKS HOLDA

        for(int i = 1; i <= 3; i ++){
            for(int j = 1; j <= 3; j++){
                indexi = (a[i][j]-1)/3+1;
                indexj = (a[i][j])%3;
                if(indexj == 0) indexj = 3;
                //String s = (MyType)board[indexi][indexj]
                if((((MyType)board[indexi][indexj].getTag()).x).equals("*")) {
                    board[indexi][indexj].setImageDrawable(getResources().getDrawable(R.drawable.nol));
                    board[indexi][indexj].setTag(new MyType("0", indexi, indexj));
                    return ;
                }
            }
        }
    }

    public void NewGame(View view) {
        cnt = 0;
        Random();
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                board[i][j].setImageDrawable(null);
                board[i][j].setTag(new MyType("*", i, j));
            }
        }
        LinearLayout layout = (LinearLayout)findViewById(R.id.status_layout);
        layout.setVisibility(View.INVISIBLE);
    }

    public void GoToMainMenu(View view) {
        this.finish();
    }
}