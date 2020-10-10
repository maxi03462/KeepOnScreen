package maxi.keep.on.screen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView intro = (TextView) findViewById(R.id.Introduccion);
        intro.setText(R.string.introduccion);
    }

}