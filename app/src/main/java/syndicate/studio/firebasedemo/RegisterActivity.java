package syndicate.studio.firebasedemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Button register1;
    private EditText email,password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register1=findViewById(R.id.register1);
        auth=FirebaseAuth.getInstance();
        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail=email.getText().toString();
                String textPassword=password.getText().toString();
                if(TextUtils.isEmpty(textEmail)||TextUtils.isEmpty(textPassword))
                    Toast.makeText(RegisterActivity.this, "Empty credentials", Toast.LENGTH_SHORT).show();
                else if (textPassword.length()<6)
                    Toast.makeText(RegisterActivity.this, "password too short", Toast.LENGTH_SHORT).show();
                else
                    registerUser(textEmail,textPassword);
            }
        });
    }

    private void registerUser(String textEmail, String textPassword) {
        auth.createUserWithEmailAndPassword(textEmail,textPassword).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    Toast.makeText(RegisterActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(RegisterActivity.this, "registration failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
