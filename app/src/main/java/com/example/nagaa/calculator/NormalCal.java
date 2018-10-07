

package com.example.nagaa.calculator;

import android.annotation.TargetApi;
import android.os.VibrationEffect;
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

    TextView txt, txt2;
     int c[]={0,0,0,0};
     int places[][]=new int [4][10];
     int k[] = new int[10];
     int l = 1;
     String val=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        txt = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);
    }

    public void btn1(View view) {
        btnActn("1");

    }

    public void btn2(View view) {
        btnActn("2");

    }

    public void btn3(View view) {
        btnActn("3");

    }

    public void btn5(View view) {
        btnActn("4");

    }

    public void btn6(View view) {
        btnActn("5");

    }

    public void btn7(View view) {
        btnActn("6");

    }

    public void btn9(View view) {
        btnActn("7");

    }

    public void btn10(View view) {
        btnActn("8");

    }

    public void btn11(View view) {
        btnActn("9");
    }

    public void btn14(View view) {
        btnActn("0");

    }

    public void btn8(View view) {
        btnActn("/");
    }

    public void btn12(View view) {
        btnActn("-");
    }

    public void btn13(View view) {
        btnActn("*");

    }

    public void btn15(View view) {
        btnActn("+");
    }

    public void btn16(View view) {
        btnActn("=");
        indexValue();


    }

    public void btn4(View view) {
        //val = val.substring(0, val.length());
        val="";
        txt.setText(val);
        txt2.setText("");
    }

    void btnActn(String op)
    {
        val = val.concat(op);
        txt.setText(val);
        VibrationEffect.createOneShot(2000,255);
    }

        void indexValue()
       {
        l=1;
        for (int i = 0; i < 4; i++)
        {
            places[i][0]=-1	;
            c[i]=0;
        }


        for (int i = 0; i < val.length (); i++)
        {
            k[0]=0;
            if (val.charAt(i) == '+'||val.charAt (i) == '-'||val.charAt (i) == '*'||val.charAt (i) =='/'||val.charAt (i) =='=' )

            {
                k[l] = i;
                l++
                ;
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


        if(places[3][0]!=-1)
            div();
        else if (places[2][0] != -1)
            mult();
        else if (places[0][0] != -1)
            add();
        else if(places[1][0]==0&&l<=3) {
            val = val.substring(0, val.length() - 1);
            txt2.setText(val);
        }
        else if (places[1][0] != -1){

            sub();
        }
        else {
            val = val.substring(0, val.length() - 1);
            txt2.setText(val);
        }
    }

     void div()
    {
        for (int i = 0; i < val.length (); i++)


        {


            if (val.charAt (i) == '/')


            {
                int indx = arraySearch (k, i);
                double arr[] = logic (i, indx);
                double divResult=0;
                try {
                     divResult = arr[0]/arr[1];
                }
                catch(Exception e)
                {
                    String s="Math Error";
                    txt2.setText(s);
                }
                StringBuilder temp = new StringBuilder (val);
                if(indx==1)
                {
                    temp.delete (k[indx - 1], k[indx + 1]);
                    temp.insert (k[indx - 1] , Double.toString (divResult));
                }

                else{
                    temp.delete (k[indx - 1]+1, k[indx + 1]);
                    temp.insert (k[indx - 1]+1 , Double.toString (divResult));
                    }
                val = temp.toString ();
                indexValue();
            }
        }
    }
    void sub()
    {
        for (int i = 0; i < val.length (); i++)
        {
            if (val.charAt (i) == '-')
            {
                int indx = arraySearch (k, i);
                double arr[] = subLogic (i, indx);
                double subResult = arr[0]-arr[1];
                StringBuilder temp = new StringBuilder (val);
                if(i==0){
                    temp.delete (0, k[indx + 2]);
                    temp.insert (k[indx-1], Double.toString (subResult));
                }
                else if(indx==1)
                {
                    temp.delete (k[indx - 1], k[indx + 1]);
                    temp.insert (k[indx - 1] , Double.toString (subResult));
                }
                else{
                    temp.delete (k[indx - 1]+1, k[indx + 1]);
                    temp.insert (k[indx - 1]+1 , Double.toString (subResult));
                    }
                val = temp.toString ();
                indexValue();
            }


        }
    }
     void add()
    {
        for (int i = 0; i < val.length (); i++)
        {
            if (val.charAt (i) == '+')
            {

                int indx = arraySearch (k, i);
                double arr[] = logic (i, indx);
                double addResult;
                if(val.charAt(k[indx-1])=='-')
                    addResult = arr[1] -arr[0];

                else
                    addResult = arr[1] +arr[0];
                StringBuilder temp = new StringBuilder (val);

                if(indx==1)
                {
                    temp.delete (k[indx - 1], k[indx + 1]);
                    temp.insert (k[indx - 1] , Double.toString (addResult));

                }

                else{
                    temp.delete (k[indx - 1], k[indx + 1]);

                    if(Double.toString (addResult).charAt(0)=='-')

                        temp.insert (k[indx - 1] , Double.toString (addResult));


                    else
                        temp.insert (k[indx - 1] ,"+"+Double.toString (addResult));
                }
                val= temp.toString ();


                indexValue();

            }


        }

    }
     void mult()
    {
        for (int i = 0; i < val.length (); i++)


        {



            if (val.charAt (i) == '*')


            {


                int indx = arraySearch (k, i);


                double arr[] = logic (i, indx);


                double multResult = arr[0] * arr[1];


                StringBuilder temp = new StringBuilder (val);

                if(indx==1)
                {
                    temp.delete (k[indx - 1], k[indx + 1]);

                    temp.insert (k[indx - 1] , Double.toString (multResult));

                }

                else{
                    temp.delete (k[indx - 1]+1, k[indx + 1]);
                    temp.insert (k[indx - 1]+1 , Double.toString (multResult));

                }
                val = temp.toString ();
                indexValue();
            }

        }
    }

     double[] subLogic (int i, int indx)


    {


        String s1, s2;



        if (i == 0)

        {


            s1 = val.substring (0, k[indx+1]);


            s2 = val.substring (k[indx+1] + 1, k[indx + 2]);


        }


        else if(indx==k[l])


        {


            s1 = val.substring (k[indx - 1], i );


            s2 = val.substring (k[indx] + 1, k[l]);


        }

        else


        {


            s1 = val.substring (k[indx - 1], i);


            s2 = val.substring (k[indx] + 1, k[indx + 1]);


        }


        double afterParse[] ={ Double.parseDouble (s1), Double.parseDouble (s2) };


        return afterParse;


    }

     double[] logic (int i, int indx)


    {
        String s1, s2;
        if (indx == 1)

        {
            s1 = val.substring (0, i);
            s2 = val.substring (k[indx] + 1, k[indx + 1]);
        }
        else if(indx==k[l])
        {
            s1 = val.substring (k[indx - 1], i );
            s2 = val.substring (k[indx] + 1, k[l]);
        }

        else
        {
            s1 = val.substring (k[indx - 1]+1, i);
            s2 = val.substring (k[indx] + 1, k[indx + 1]);
        }

        double afterParse[]=new double[2];
        try {
            afterParse[0] =Double.parseDouble(s1);
            afterParse[1]=Double.parseDouble(s2);

        }
        catch(Exception ob){
            String s="Illegal Expression";
            txt2.setText(s);
        }
        return afterParse;
    }


     int arraySearch(int k[],int key)
    {
        int in=-1;
        for(int i=0;i<l;i++)
        {
            if(k[i]==key)
                in=i;
        }
        return in;
    }
}

