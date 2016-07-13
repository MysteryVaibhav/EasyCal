package easycal.com.easycal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txtSpeechInput;
    private TextView resultText;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    //TODO: Temp variables below to be removed later
    private final String[] nums = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                                    "0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final String[] operator = {"plus", "minus", "-", "+"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        resultText = (TextView) findViewById(R.id.result);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        // hide the action bar
        //getActionBar().hide();

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String input = result.get(0);
                    String output = computeValue(input);
                    txtSpeechInput.setText(input);
                    if(output!=null) {
                        resultText.setText(output);
                    } else {
                        resultText.setText("Sorry, didn't get you !!!");
                    }
                }
                break;
            }

        }
    }

    /**
     * TODO: @Kailash, imlpement this properly
     * Takes a string as input and returns a mathematical expression as output in a string
     * @param input
     * @return
     */
    private String computeValue(String input) {
        String result = null;
        input = input.toLowerCase();
        for(String op: operator) {
            if(input.contains(op)) {
                String[] parts = input.split(op);
                String left = null;
                String right = null;
                int leftNum = 0;
                int rightNum = 0;
                if(parts.length==2) {
                    int t=0;
                    for (String num : nums) {
                        if (parts[0].contains(num)) {
                            left = num;
                            leftNum = t%11;
                            break;
                        }
                        t++;
                    }
                    t=0;
                    for (String num : nums) {
                        if (parts[1].contains(num)) {
                            right = num;
                            rightNum = t%11;
                            break;
                        }
                        t++;
                    }
                    if(left!=null && right!=null) {
                        String oprt = op.equals("-") || op.equals("minus")?"-":"+";
                        int out = op.equals("-") || op.equals("minus")?leftNum-rightNum:leftNum+rightNum;
                        result = leftNum + " " + oprt + " " + rightNum + " = " + out;
                    }
                }
            }
        }
        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}