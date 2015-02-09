package sawnoff.mdtr;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;


public class MainScreen extends ActionBarActivity {
    TextView mainTextView;
    LinkedList availableTypes = new LinkedList();
    Random rand = new Random();
    EditText mainEditText;
    Button submitButton;
    TextView mainAnswerView;
    Double answer;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        mainTextView = (TextView) findViewById(R.id.main_textview);
        getType();
        mainEditText = (EditText) findViewById(R.id.main_edittext);
        submitButton = (Button) findViewById(R.id.main_button);
        mainAnswerView = (TextView) findViewById(R.id.main_answerview);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               answer = Double.parseDouble(mainEditText.getText().toString());
               compareAnswer();
               //mainAnswerView.setText(answer+result);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void getType() {
        availableTypes.add("temp");
        String chosen = (String) availableTypes.get(rand.nextInt(availableTypes.size()));
        switch (chosen) {
            case "temp":
                askTemp();
        }
    }
    private void askTemp() {
        int x = rand.nextInt((30 - 10) + 1) + 10;
        int y = rand.nextInt(((60 - 20) +1) +20) *5;
        String question = "Temperature at sea level is " + x + " degrees. At FL"+y+" what is the temperature?";
        mainTextView.setText(question);
        result = x-(y*2/10);
    }
    private void compareAnswer() {
        if (result==answer) mainAnswerView.setText("Correct!");
        else mainAnswerView.setText("Incorrect :(");

    }
}
