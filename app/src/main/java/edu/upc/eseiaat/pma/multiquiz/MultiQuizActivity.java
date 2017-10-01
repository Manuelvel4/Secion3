package edu.upc.eseiaat.pma.multiquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MultiQuizActivity extends AppCompatActivity {

    private int ids_answers[]={R.id.answer1, R.id.answer2,R.id.answer3, R.id.answer4};
    private int correct_answer;
    private int[]answer;
    private String[] all_question;
    private TextView test_question;
    private boolean[] answer_correct;
    private int corrent_question;
    private RadioGroup grup;
    private Button btn_next;
    private Button btn_previus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_quiz);

        test_question = (TextView)findViewById(R.id.test_question);
        grup = (RadioGroup) findViewById(R.id.radioGroup); // son mis referencias a objetos de la pantalla
        all_question = getResources().getStringArray(R.array.all_question);

        // lo hago aki porque ya se cuantas preguntas tengo
        answer_correct = new  boolean[all_question.length];
        answer = new int[all_question.length];

        for (int i =0; i < answer.length; i++){
            answer[i] = -1;
            // no se lo que has respondido es la 0,1,????
        }



        corrent_question = 0;
        String g = all_question[0];
        String [] parts = g.split(";");




        // TODO; cuando clickan el voton tienen que pasar a la siguiente pagina
        // TODO con el boton

        btn_next = (Button) findViewById(R.id.btn_check);
        btn_previus = (Button) findViewById(R.id.bnt_previus); // declaro mi boton
       // declaro el otro boton





        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   /*
                if(answer == correct_answer){Toast.makeText(MultiQuizActivity.this,R.string.Correct, Toast.LENGTH_SHORT).show();}
                else {Toast.makeText(MultiQuizActivity.this,R.string.Mistake, Toast.LENGTH_SHORT).show();} */


                // quitamos el toasht

                CheckAnswer();
                    // con esto de arriba guardo las que son correctas;

                if (corrent_question < all_question.length -1){
                corrent_question ++;
                ShowQuestion();}

                else {
                    int correct = 0, incorrect =0;
                    for (boolean b : answer_correct){
                        if(b) correct++;
                        else incorrect++;
                    }
                    String resultado =
                            String.format("Correctas : %d --- Incorrectas: %d",  correct , incorrect );

                    Toast.makeText(MultiQuizActivity.this,resultado, Toast.LENGTH_SHORT).show();

                }



                /*for(int i= 0 ; i<answer_correct.length ; i++){

                    Log.i("CORRECION", String.format("respuesta %d: %b {%b}", i, answer[i], answer_correct[i]));



                } */
            }});
        btn_previus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswer();

                if(corrent_question > 0){corrent_question --;}
            ShowQuestion();


            }
        });


    }

    private void CheckAnswer() {
        int id = grup.getCheckedRadioButtonId();
        int ans = -1;
        for(int i = 0; i < ids_answers.length; i++){


            if (ids_answers[i]==id){ans =i;}}


        answer_correct [corrent_question] = (ans == correct_answer);
        answer [corrent_question] = ans;
    }

    private void ShowQuestion() {
        String q = all_question[corrent_question];
        String [] parts = q.split(";");
        test_question.setText(parts[0]);
        grup.clearCheck();

        for(int i=0 ; i <ids_answers.length ; i++){
            RadioButton ans1 = (RadioButton) findViewById(ids_answers[i]);
            String answers = parts[i+1];
            if (answers.charAt(0) == 'f'){
                correct_answer = i;
                answers = answers.substring(1);
            }

            ans1.setText(answers);

            if(answer[corrent_question] == i){ans1.setChecked(true);}
        }

        if (corrent_question == 0) { btn_previus.setVisibility(View.GONE);
        }
        else {btn_previus.setVisibility(View.VISIBLE);}


        if (corrent_question == all_question.length -1){
            btn_next.setText(R.string.finish);}
        else {btn_next.setText(R.string.Next);}

    }
}
