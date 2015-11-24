package tech.kcl.api_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import org.joda.time.DateTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        outputLine("KCL Tech Build X: Android");
        outputLine("Lecture Seven");
        outputLine("Networking and APIs");
        outputLine("--");

        // put your code here!

    }

    /*-------------------------*
     | You don't need to touch |
     |   the code down here!   |
     *-------------------------*/

    private TextView outputView;
    private String output = "";

    protected void init() {
        outputView = (TextView) findViewById(R.id.output);
    }

    protected void clearOutput() {
        output = "";
        outputView.setText("");
    }

    protected void outputLine(String s) {
        output += (new DateTime()).toString("HH:mm:ss") + " <strong>" + s + "</strong><br />";
        outputView.setText(Html.fromHtml(output));
    }
}
