package lt.pusnis.multibarcodereader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    MaterialButton mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpBtnTakPhoto();
    }

    private void setUpBtnTakPhoto() {
        mBtn = findViewById(R.id.mBtn);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MyCameraActivity.class);
                startActivity(intent);

            }
        };
    }
}