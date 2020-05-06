package syndicate.studio.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    private EditText mail,pass;
    private Button login;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login3);
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.pass2);
        auth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail=mail.getText().toString();
                String textPassword=pass.getText().toString();
                loginUser(textEmail,textPassword);
            }
        });

    }

    private void loginUser(String textEmail, String textPassword) {
        auth.signInWithEmailAndPassword(textEmail,textPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(loginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(loginActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
