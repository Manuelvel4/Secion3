package edu.upc.eseiaat.pma.multiquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MultiQuizActivity extends AppCompatActivity {

    private int ids_answers[]={R.id.answer1, R.id.answer2,R.id.answer3, R.id.answer4};
    private int correct_answer;

    private String[] all_question;
    private TextView test_question;
    private int corrent_question;
    private RadioGroup grup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_quiz);

        test_question = (TextView)findViewById(R.id.test_question);
        final RadioGroup grup = (RadioGroup) findViewById(R.id.radioGroup); // son mis referencias a objetos de la pantalla
        all_question = getResources().getStringArray(R.array.all_question);
        corrent_question = 0;
        String g = all_question[0];
        String [] parts = g.split(";");
        ShowQuestion();


        // TODO; cuando clickan el voton tienen que pasar a la siguiente pagina
        // TODO con el boton

        Button btn_check = (Button) findViewById(R.id.btn_check); // declaro mi boton


        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = grup.getCheckedRadioButtonId();
                int answer = -1;
                for(int i = 0; i < ids_answers.length; i++){if (ids_answers[i]==id){answer =i;}}
                if(answer == correct_answer){Toast.makeText(MultiQuizActivity.this,R.string.Correct, Toast.LENGTH_SHORT).show();}
                else {Toast.makeText(MultiQuizActivity.this,R.string.Mistake, Toast.LENGTH_SHORT).show();}
                corrent_question ++;
                ShowQuestion();}

        }

        // final del boton


        );
        //TODO poner un boton que cambia a la siguiente


    }

    private void ShowQuestion() {
        String q = all_question[corrent_question];
        String [] parts = q.split(";");

        test_question.setText(parts[0]);
        RadioButton ans1 = (RadioButton)findViewById(R.id.answer1);
        for(int i=0 ; i <ids_answers.length ; i++){
            RadioButton rb = (RadioButton) findViewById(ids_answers[i]);
            String answer = parts[i+1];
            if (answer.charAt(0) == 'Q'){

                correct_answer = i;
            }

            rb.setText(answer);
        }
    }
}
