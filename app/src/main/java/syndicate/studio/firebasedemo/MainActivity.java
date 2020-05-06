package syndicate.studio.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button logout,add;
    private EditText name;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout=findViewById(R.id.logout);
        add=findViewById(R.id.add);
        name=findViewById(R.id.name);
        listView=findViewById(R.id.listview);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*HashMap<String,Object> branch1=new HashMap<>();
                branch1.put("name","ashutosh");
                branch1.put("email","ashutoshbiswal211299");
                HashMap<String,Object> branch2=new HashMap<>();
                branch2.put("name","lama");
                branch2.put("email","lama@gamil.");
                HashMap<String,Object> branch3=new HashMap<>();
                branch3.put("name","ruchi");
                branch3.put("email","ruchichutiya");
                HashMap<String,Object> base=new HashMap<>();
                base.put("branch1",branch1);
                base.put("branch2",branch2);
                base.put("branch3",branch3);

                FirebaseDatabase.getInstance().getReference().child("Info").updateChildren(base);
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();*/

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,StartActivity.class));
            }
        });
        /*FirebaseDatabase.getInstance().getReference().child("knowledge").child("name").setValue("ashutosh");
        HashMap<String,Object> map=new HashMap<>();
        map.put("name","Ashutosh");
        map.put("mail","aaronstone201299@gmail.com");
        FirebaseDatabase.getInstance().getReference().child("knowledge").child("details").updateChildren(map);*/

        final ArrayList<String> list=new ArrayList<>();
        final ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.list_item,list);
        listView.setAdapter(adapter);
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Info");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    info in=snapshot.getValue(info.class);
                    String txt=in.getName()+":"+ in.getEmail();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
