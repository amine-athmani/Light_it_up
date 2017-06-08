package athmanitech.etic2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Inscription extends AppCompatActivity {
    DB db;
    EditText nom,prenom,username,password,email;
    Button inscrir,camera;
    ImageView photoView;
    File photoFileS;
    private static final int CAMERA_PIC_REQUEST = 001;
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        // Creer la base de données
        db = new DB(this);
        db.open();
        // Pointer sur les compsantes
        nom = (EditText) findViewById(R.id.nom_inscription);
        prenom = (EditText) findViewById(R.id.prenom_inscription);
        password = (EditText) findViewById(R.id.password_inscription);
        username = (EditText) findViewById(R.id.username_inscription);
        email = (EditText) findViewById(R.id.email_inscription);
        inscrir = (Button) findViewById(R.id.continue_inscription);
        camera = (Button) findViewById(R.id.cam_inscription);
        photoView = (ImageView) findViewById(R.id.imageView);

        // On click Methods
        inscrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNom= nom.getText().toString();
                String sPrenom= prenom.getText().toString();
                String sPassword= password.getText().toString();
                String sUsername= username.getText().toString();
                String sEmail= email.getText().toString();
                // Controle de saisie
                if (sNom.equals("") | sNom.equals("") | sNom.equals("") | sNom.equals("") | sNom.equals("") ) {
                    Toast.makeText(Inscription.this,"Tous les champs sont obligatoires", Toast.LENGTH_LONG).show();
                }
                else {
                    if (db.getUsernamePassword(sUsername, sPassword).getCount() > 0) {
                        Toast.makeText(Inscription.this, "You are already enlighted", Toast.LENGTH_LONG).show();
                    } else {
                        db.inscription(sNom, sPrenom, sUsername, sPassword, sEmail, mCurrentPhotoPath);
                        Toast.makeText(Inscription.this, "Light your way with us", Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(Inscription.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
                photoFileS = photoFile;
            } catch (IOException e) {
            }
            if (photoFile != null)
            {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = Environment.getExternalStorageDirectory();
        File storageDir2 = new File(storageDir.getAbsolutePath());

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir2      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();

        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            photoView.setImageURI(Uri.parse(mCurrentPhotoPath));
        }
    }

}