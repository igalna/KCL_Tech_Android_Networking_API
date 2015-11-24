package tech.kcl.api_example;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;

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

        outputLine("Request Started..");

        new ApiRequest().execute("http://api.worldbank.org/countries/?format=json&per_page=30");

    }

    private class ApiRequest extends AsyncTask<String, Double, String> {

        @Override
        protected String doInBackground(String... params) {

            // check input
            if (params.length != 1)
                return null;

            // lots of strings
            try {
                // open a connection
                URL url = new URL(params[0]);

                // create an input stream from the URL and put it in a DataInputStream
                InputStream inStream = url.openStream();
                DataInputStream dataInputStream = new DataInputStream(inStream);

                // create a buffer
                byte[] buffer = new byte[1024];
                int bufferLength;

                // output builder to build our output
                ByteArrayOutputStream output = new ByteArrayOutputStream();

                // actually read some data
                while ((bufferLength = dataInputStream.read(buffer)) > 0) {
                    // write buffer into output
                    output.write(buffer, 0, bufferLength);
                }

                // return our output
                return output.toString("UTF-8");

            } catch (Exception e) {
                // anything went wrong
                e.printStackTrace();
                outputLine("Something went wrong");
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // check for null
            if (result == null)
                return;

            // show the result to the user
            outputLine(result);
        }
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
