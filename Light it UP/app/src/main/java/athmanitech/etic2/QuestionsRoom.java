package athmanitech.etic2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class QuestionsRoom extends AppCompatActivity {
    DB db;
    List<Question> questions;
    List<String> questionsSL;
    QuestionAdapter questionAdapter;
    ListView listView;
    String userId;
    String room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_room);

        db = new DB(this);
        db.open();
        // Recuperer l'intente pour afficher les questions du Room
        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.AskRoom);
        Cursor listAsk = db.getQuestion(room);
        questions = new ArrayList<Question>();
        questionsSL = new ArrayList<String>();
        Button addQuestion = (Button) findViewById(R.id.AddQuestion);

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("TestIntent",""+room + " user : "+ userId);
                Intent intent = new Intent(QuestionsRoom.this,AccueilQuestion.class);
                /*intent.putExtra("idRoom",room);
                intent.putExtra("idUser",userId);*/
                startActivity(intent);
            }
        });

        for (listAsk.moveToFirst(); !listAsk.isAfterLast(); listAsk.moveToNext())
        {
            Question rn = new Question();
            rn.setId(Integer.parseInt(listAsk.getString(0)));
            rn.setTitre(listAsk.getString(1));
            questions.add(rn);
            questionsSL.add(listAsk.getString(0));
        }
        questionAdapter = new QuestionAdapter(this,questions,questionsSL,db);
        listView.setAdapter(questionAdapter);
    }
}
