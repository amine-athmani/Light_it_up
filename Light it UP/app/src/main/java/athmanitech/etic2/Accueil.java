package athmanitech.etic2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Accueil extends AppCompatActivity {
    DB db;
    private AlertDialog myAlertDialog=null;
    List<Room> rooms;
    ListView listView;
    RoomAdapter roomAdapter;
    List<String> roomSL;

    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        db = new DB(this);
        db.open();
        // Recuperer l'intent et les extras : nom , prenom , image
        Intent intent= getIntent();
        String image = intent.getExtras().getString("image");
        String nom = intent.getExtras().getString("nom");
        String prenom = intent.getExtras().getString("prenom");
        String id = intent.getExtras().getString("id");
        // Mettre l'image sur l'image container
        ImageView imageContainer = (ImageView) findViewById(R.id.Image_user);
        imageContainer.setImageURI(Uri.parse(image));

        TextView textView1 = (TextView) findViewById(R.id.nomProfil);
        TextView textView2 = (TextView) findViewById(R.id.prenomProfile);
        TextView textView3 = (TextView) findViewById(R.id.idProfile);
        Button room = (Button) findViewById(R.id.AddRoom);

        textView1.setText(nom);
        textView2.setText(prenom);
        textView3.setText(id);
        listView = (ListView) findViewById(R.id.listRoom);

        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appelBoiteDialogue();
            }
        });

        rooms = new ArrayList<Room>();
        roomSL = new ArrayList<String>();
        Cursor listRoom = db.getRooms();
        for (listRoom.moveToFirst(); !listRoom.isAfterLast(); listRoom.moveToNext())
        {
            Room rn = new Room();
            rn.setId(Integer.parseInt(listRoom.getString(0)));
            rn.setTitre(listRoom.getString(1));
            rooms.add(rn);
            roomSL.add(listRoom.getString(0));
        }
        roomAdapter = new RoomAdapter(this,rooms,roomSL);
        listView.setAdapter(roomAdapter);
    }

    protected void appelBoiteDialogue() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(Accueil.this);
        myDialog.setTitle("Add Room");
        final EditText editText = new EditText(Accueil.this);

        LinearLayout layout = new LinearLayout(Accueil.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(editText);
        myDialog.setView(layout);

        myDialog.setPositiveButton("Inserer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (!editText.getText().toString().equals("")) {
                    db.addRoom(editText.getText().toString());
                }
                Toast.makeText(Accueil.this, "Your Room has been created", Toast.LENGTH_SHORT).show();
            }
        });
        myDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        myAlertDialog = myDialog.show();
    }
}
