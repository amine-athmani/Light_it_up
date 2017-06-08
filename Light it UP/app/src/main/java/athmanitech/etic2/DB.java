package athmanitech.etic2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Athmani on 10/02/2017.
 */
public class DB {
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Context context;

    public DB(Context context)
    {
        this.context=context;
        this.dbHelper=new DatabaseHelper(context);
    }

    public class DatabaseHelper extends SQLiteOpenHelper {
        Context context;

        // Creer les tables
        public DatabaseHelper (Context context) {
            super(context,"Training Camp",null,1);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS user("
                    + " id integer primary key autoincrement ,"
                    + " nom text ,"
                    + " prenom text ,"
                    + " email text ,"
                    + " username text ,"
                    + " password text ,"
                    + " image text"
                    + " );");
            db.execSQL("CREATE TABLE IF NOT EXISTS room("
                    + " id integer primary key autoincrement ,"
                    + " titre text ,"
                    + " icon text "
                    + " );");
            db.execSQL("CREATE TABLE IF NOT EXISTS question("
                    + " id integer primary key autoincrement ,"
                    + " titre text ,"
                    + " content text ,"
                    + " fk_user integer ,"
                    + " fk_room integer ,"
                    + " vote integer"
                    + " );");
            db.execSQL("CREATE TABLE IF NOT EXISTS reponse("
                    + " id integer primary key autoincrement ,"
                    + " content text ,"
                    + " fk_user integer ,"
                    + " fk_question integer ,"
                    + " vote integer"
                    + " );");
            db.execSQL("CREATE TABLE IF NOT EXISTS user_reponse("
                    + " id integer primary key autoincrement ,"
                    + " fk_user integer ,"
                    + " fk_reponse integer ,"
                    + " typevote text"
                    + " );");
            db.execSQL("CREATE TABLE IF NOT EXISTS user_question("
                    + " id integer primary key autoincrement ,"
                    + " fk_user integer ,"
                    + " fk_question integer ,"
                    + " typevote text"
                    + " );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }

    public void inscription(String nom, String prenom , String username , String password , String email , String image )
    {
        ContentValues value = new ContentValues();
        value.put("nom",nom);
        value.put("prenom",prenom);
        value.put("username",username);
        value.put("password",password);
        value.put("email",email);
        value.put("image",image);
        db.insert("user",null,value);
    }

    public void addRoom(String titre )
    {
        ContentValues value = new ContentValues();
        value.put("titre", titre);
        db.insert("room",null,value);
    }

    public void addQuestion (String titre , String content , int vote)
    {
        ContentValues value = new ContentValues();
        value.put("titre",titre);
        value.put("content",content);
        value.put("vote",vote);
        db.insert("question",null,value);
    }

    public Cursor getUsernamePassword (String username , String password)
    {
        return db.rawQuery("SELECT user.id, user.prenom, user.nom, user.image FROM user WHERE user.username = '"+username+
                "' and user.password = '"+password+"'",null);
    }

    public Cursor getRooms ()
    {
        return db.rawQuery("SELECT room.id, room.titre FROM room",null);
    }

    public Cursor getQuestion(String room)
    {
        return db.rawQuery("SELECT question.id, question.titre, question.content, question.fk_user, question.vote FROM question WHERE question.fk_room = "+
        room,null);
    }

    public Cursor getUser(int userId)
    {
        return db.rawQuery("SELECT user.username, user.image FROM user WHERE user.id = "+userId,null);
    }

    public DB open()
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

}
