package athmanitech.etic2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Athmani on 11/02/2017.
 */
public class QuestionAdapter extends ArrayAdapter<String> {
    Context c;
    List<Question> questions;
    List<String> ids;
    DB db;

    public QuestionAdapter (Context context , List<Question> questions , List<String> ids , DB db ) {
        super(context, R.layout.itemroom , R.id.RoomTitle , ids );
        this.c = context;
        this.questions = questions;
        this.ids = ids;
        this.db = db;
    }

    public View getView(final int position , View convertView , ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.itemask,parent,false);
        ImageView imageView = (ImageView) row.findViewById(R.id.UserPic);
        TextView userInfo = (TextView) row.findViewById(R.id.UserInfo);
        TextView questionTitle = (TextView) row.findViewById(R.id.QuestionTitle);

        /*Cursor user = db.getUser(questions.get(position));
        user.moveToPosition(0);
        try {
            imageView.setImageURI(Uri.parse(user.getString(1)));
        }catch (Exception e)
        {
        }*/
        //userInfo.setText(user.getString(0));
        userInfo.setText("ETIC");
        questionTitle.setText("Hello World");//questions.get(position).getTitre());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),QuestionsRoom.class);
                //intent.putExtra("id",rooms.get(position).getId());
                getContext().startActivity(intent);
            }
        });
        return row;
    }
}
