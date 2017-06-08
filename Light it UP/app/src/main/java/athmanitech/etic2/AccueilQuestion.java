package athmanitech.etic2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AccueilQuestion extends AppCompatActivity {
    Boolean rouge = true;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_question);

        db = new DB(this);
        db.open();


        final int vote = 200;
        LinearLayout rougeArea = (LinearLayout) findViewById(R.id.RedButton);
        final LinearLayout blue = (LinearLayout) findViewById(R.id.BlueButton);
        final EditText coins = (EditText) findViewById(R.id.Coins);
        Button post = (Button) findViewById(R.id.PostQ);
        final EditText title = (EditText) findViewById(R.id.QuestionTitleAdd);
        final EditText contenu = (EditText) findViewById(R.id.QuestionContent);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titre = title.getText().toString();
                String contenuDB = contenu.getText().toString();
                db.addQuestion(titre,contenuDB,vote);
                Toast.makeText(AccueilQuestion.this, "Added succesfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AccueilQuestion.this,QuestionsRoom.class);
                startActivity(intent);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coins.setVisibility(View.GONE);
            }
        });
        rougeArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coins.setVisibility(View.VISIBLE);
            }
        });
    }
}
