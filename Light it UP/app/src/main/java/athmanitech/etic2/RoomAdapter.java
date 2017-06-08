package athmanitech.etic2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Athmani on 10/02/2017.
 */
public class RoomAdapter extends ArrayAdapter<String> {
    Context c;
    List<Room> rooms;
    List<String> ids;
    String userid;

    public RoomAdapter(Context context , List<Room> rooms , List<String> ids) {
        super(context, R.layout.itemroom , R.id.RoomTitle , ids );
        this.c = context;
        this.rooms = rooms;
        this.ids = ids;
    }

    public View getView(final int position , View convertView , ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.itemroom,parent,false);
        TextView textView= (TextView) row.findViewById(R.id.RoomTitle);

        textView.setText(rooms.get(position).getTitre());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),QuestionsRoom.class);
                /*intent.putExtra("id",rooms.get(position).getId()+"");
                intent.putExtra("userid",userid);*/
                getContext().startActivity(intent);
            }
        });
        return row;
    }
}
