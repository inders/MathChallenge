package multiplicationchallenge.mindgames.com.mulitiplicationchallenge;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by indersingh on 9/9/16.
 */
public class ProfileActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Toast correctToast = Toast.makeText(this, "Got it", Toast.LENGTH_SHORT);
        final Toast errorToast = Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT);
        final Intent mainActivityIntent = new Intent(this, MainActivity.class);

        setContentView(R.layout.profile_layout);
        final EditText userName = (EditText) findViewById(R.id.nameEditText);
        FancyButton saveUserName = (FancyButton) findViewById(R.id.saveUserName);
        saveUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString();
                if (name != null && name.length() > 0) {
                    SharedPreferences pref = getApplicationContext().
                            getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(Constants.PROFILE_NAME, name);
                    editor.apply();
                    correctToast.show();
                    startActivity(mainActivityIntent);
                } else {
                    errorToast.show();
                }
            }
        });

    }
}
