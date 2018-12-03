package com.example.volk1.fivecheckboxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String messages = "";
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn = findViewById(R.id.showtoast_btn);
    }

    public void onClick(View view) {
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayToast("Toppings: " + messages);
            }
        });
    }

    public String checkedCB(View view) {
        boolean checked = ((CheckBox)view).isChecked();
        switch (view.getId()) {
            case R.id.chocolate_cb:
                if (checked)
                    messages += getString(R.string.chocolate_syrup) + " ";
                break;
            case R.id.sprincles_cb:
                if (checked)
                    messages += getString(R.string.sprinkles) + " ";
                break;
            case R.id.nuts_cb:
                if (checked)
                    messages += getString(R.string.crushed_nuts) + " ";
                break;
            case R.id.cherries_cb:
                if (checked)
                    messages += getString(R.string.cherries) + " ";
                break;
            case R.id.cookie_cb:
                if (checked)
                    messages += getString(R.string.orio_cookie_crumbles) + " ";
                break;
            default:
                break;
        }
        return messages;
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
