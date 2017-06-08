package athmanitech.etic2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText username,password;
    Button inscrption,login;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DB(this);
        db.open();

        username = (EditText) findViewById(R.id.username_button);
        password = (EditText) findViewById(R.id.password_button);
        inscrption = (Button) findViewById(R.id.inscription_button);
        login = (Button) findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                Cursor verification = db.getUsernamePassword(user,pass);
                if(verification.getCount() > 0) {
                    verification.moveToPosition(0);
                    String id = verification.getString(0);
                    String prenom = verification.getString(1);
                    String nom = verification.getString(2);
                    String image = verification.getString(3);
                    Intent intent = new Intent(MainActivity.this,Accueil.class);
                    intent.putExtra("id",id);
                    intent.putExtra("prenom",prenom);
                    intent.putExtra("nom",nom);
                    intent.putExtra("image",image);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Hbibna rak Ghalet , veuillez retaper le Password",Toast.LENGTH_LONG).show();
                }
            }
        });
        inscrption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Basculer vers l'UI Inscription
                Intent intent = new Intent(MainActivity.this,Inscription.class);
                startActivity(intent);
            }
        });
    }
}
