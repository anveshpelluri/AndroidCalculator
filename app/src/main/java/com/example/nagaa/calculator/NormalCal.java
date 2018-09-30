package com.example.nagaa.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.*;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class NormalCal extends AppCompatActivity {
 String val=" ";
 TextView txt,txt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        txt = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);
    }

    public void btn1(View view){
        val=val.concat("1");
     txt.setText(val);
    }
    public void btn2(View view){
        val=val.concat("2");
        txt.setText(val);
    }
    public void btn3(View vie0w){
        val=val.concat("3");
        txt.setText(val);
    }
    public void btn5(View view){
        val=val.concat("4");
        txt.setText(val);
    }
    public void btn6(View view){
        val=val.concat("5");
        txt.setText(val);
    }
    public void btn7(View view){
        val=val.concat("6");
        txt.setText(val);
    }
    public void btn9(View view){
        val=val.concat("7");
        txt.setText(val);
    }
    public void btn10(View view){
        val=val.concat("8");
        txt.setText(val);
    }
    public void btn11(View view){
        val=val.concat("9");
        txt.setText(val);
    }
    public void btn14(View view){
        val=val.concat("0");
        txt.setText(val);
    }
    public void btn8(View view){
        val=val.concat("/");
        txt.setText(val);
    }
    public void btn12(View view){
        val=val.concat("-");
        txt.setText(val);
    }
    public void btn13(View view){
        val=val.concat("*");
        txt.setText(val);
    }
    public void btn15(View view){
        val=val.concat("+");
        txt.setText(val);
    }
    public void btn16(View view){
        val=val.concat("=");
        txt.setText(val);
        equalbtn();
    }
    public void btn4(View view){
        val=val.substring(0,(val.length()-3));
        txt.setText(val);
    }
    int places[][]=new int[4][10];
    int k[]=new int[100];
    int c[]={0,0,0,0};
    public void equalbtn( )
    {   StringBuffer temp=new StringBuffer(val);
        StringBuffer temp1;
        int a=temp.lastIndexOf("=");
        temp1=temp.deleteCharAt(a);
        val=temp1.toString();
             logic();
    }
        void logic(){
        int l=0;
       for(int i=1;i<val.length();i++)
       {
           if(val.charAt(i)=='+'||val.charAt(i)=='-'||val.charAt(i)=='*'||val.charAt(i)=='/') {
               k[l] = i;
               l++;
           }
           switch (val.charAt(i)) {
               case '+':
                   places[0][c[0]] = i;
                   ++c[0];
                   break;
               case '-':
                   places[1][c[1]] = i;
                   ++c[1];
                   break;
               case '*':
                   places[2][c[2]] = i;
                   ++c[2];
                   break;
               case '/':
                   places[3][c[3]] = i;
                   ++c[3];
                   break;
           }
       }
       if(places[3][0]!=0)
            div();
            else if (places[2][0] != 0)
                mult();
            else if (places[1][0] != 0)
                subtract();
            else if (places[0][0] != 0)
                add();
            else {
                txt2.setText(val);
            }
    }
    void div() {
        int indx[] = new int[c[3]];
        for (int i = 0; i < c[3]; i++) {
            String s1, s2;
            indx[i] = Arrays.binarySearch(k, places[3][i]);
            if (indx[i] == k[0]) {
                s2 = val.substring(k[indx[i] + 1], k[indx[i+1]]);
                txt2.setText(s2);}
                /*s1 = val.substring(k[0], k[indx[i]]);
                StringBuffer temp = new StringBuffer(val);
                double a = Double.parseDouble(s1) / Double.parseDouble(s2);
                temp.replace(k[0], k[indx[i + 1]], Double.toString(a));
                val = temp.toString();
            } else {
                s1 = val.substring(k[indx[i - 1] + 1], k[indx[i]]);
                s2 = val.substring(k[indx[i] + 1], k[indx[i + 1]]);
                double a = Double.parseDouble(s1) / Double.parseDouble(s2);
                StringBuffer temp = new StringBuffer(val);
                temp.replace(k[indx[i - 1] + 1], k[indx[i + 1]], Double.toString(a));
                val = temp.toString();
            }*/
        }
       places[3][0]=0;
        logic();
    }

    void mult() {
        int indx[] = new int[c[2]];
        for(int i=0;i<c[2];i++) {
            String s1, s2;
            indx[i] = Arrays.binarySearch(k, places[2][i]);
            if (indx[i] == k[0]) {
                s2 = val.substring(k[indx[i] + 1], k[indx[i]]);
                s1 = val.substring(k[0], k[indx[i]]);
                StringBuffer temp = new StringBuffer(val);
                double a = Double.parseDouble(s1) * Double.parseDouble(s2);
                temp.replace(k[0], k[indx[i + 1]], Double.toString(a));
                val = temp.toString();
            } else {
                s1 = val.substring(k[indx[i - 1] + 1], k[indx[i]]);
                s2 = val.substring(k[indx[i] + 1], k[indx[i + 1]]);
                double a = Double.parseDouble(s1) * Double.parseDouble(s2);
                StringBuffer temp = new StringBuffer(val);
                temp.replace(k[indx[i - 1] + 1], k[indx[i + 1]], Double.toString(a));
                val = temp.toString();
            }

            places[3][1]=0;
            logic();

        }
    }
        void subtract() {
            int indx[] = new int[c[1]];
            for (int i = 0; i < c[3]; i++) {
                String s1, s2;
                indx[i] = Arrays.binarySearch(k, places[1][i]);
                if (indx[i] == k[0]) {
                    s2 = val.substring(k[indx[i] + 1], k[indx[i]]);
                    s1 = val.substring(k[0], k[indx[i]]);
                    StringBuffer temp = new StringBuffer(val);
                    double a = Double.parseDouble(s1) - Double.parseDouble(s2);
                    temp.replace(k[0], k[indx[i + 1]], Double.toString(a));
                    val = temp.toString();
                } else {
                    s1 = val.substring(k[indx[i - 1] + 1], k[indx[i]]);
                    s2 = val.substring(k[indx[i] + 1], k[indx[i + 1]]);
                    double a = Double.parseDouble(s1) - Double.parseDouble(s2);
                    StringBuffer temp = new StringBuffer(val);
                    temp.replace(k[indx[i - 1] + 1], k[indx[i + 1]], Double.toString(a));
                    val = temp.toString();
                }
            }

            places[1][0]=0;
            logic();
        }
    void add() {
        int indx[] = new int[c[0]];
        for(int i=0;i<c[3];i++){
            String s1,s2;
            indx[i]= Arrays.binarySearch(k ,places[0][i]);
            if(indx[i]==k[0])
            {
                s2=val.substring(k[indx[i]+1],k[indx[i]]);
                s1=val.substring(k[0],k[indx[i]]);
                StringBuffer temp=new StringBuffer(val);
                double a=Double.parseDouble(s1)+Double.parseDouble(s2);
                temp.replace(k[0],k[indx[i+1]],Double.toString(a));
                val=temp.toString();
            }
            else {
                s1 = val.substring(k[indx[i - 1] + 1], k[indx[i]]);
                s2 = val.substring(k[indx[i] + 1], k[indx[i + 1]]);
                double a=Double.parseDouble(s1)+Double.parseDouble(s2);
                StringBuffer temp=new StringBuffer(val);
                temp.replace(k[indx[i-1]+1],k[indx[i+1]],Double.toString(a));
                val=temp.toString();
            }

        }
        places[3][0]=0;
        logic();
    }
}
