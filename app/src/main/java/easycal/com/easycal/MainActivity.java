package easycal.com.easycal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
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

    //Calculator
    Button button0 , button1 , button2 , button3 , button4 , button5 , button6 ,
            button7 , button8 , button9 , buttonAdd , buttonSub , buttonDivision ,
            buttonMul , button10 , buttonC , buttonEqual ;
    float mValueOne , mValueTwo ;
    boolean mAddition , mSubtract ,mMultiplication ,mDivision ;

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

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        buttonAdd = (Button) findViewById(R.id.buttonadd);
        buttonSub = (Button) findViewById(R.id.buttonsub);
        buttonMul = (Button) findViewById(R.id.buttonmul);
        buttonDivision = (Button) findViewById(R.id.buttondiv);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonEqual = (Button) findViewById(R.id.buttoneql);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
        //Calculator functions

        //Number functions
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult("0", true);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult("1", true);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult("2", true);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult("3", true);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult("4", true);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult("5", true);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult("6", true);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult("7", true);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult("8", true);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult("9", true);
            }
        });

        //Operators
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mValueOne = Float.parseFloat(resultText.getText() + "");
                    mAddition = true;
                    resultText.setText(null);
                    txtSpeechInput.setText(txtSpeechInput.getText()+" + ");
                }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(resultText.getText() + "");
                mSubtract = true ;
                resultText.setText(null);
                txtSpeechInput.setText(txtSpeechInput.getText()+" - ");
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(resultText.getText() + "");
                mMultiplication = true ;
                resultText.setText(null);
                txtSpeechInput.setText(txtSpeechInput.getText()+" * ");
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(resultText.getText()+"");
                mDivision = true ;
                resultText.setText(null);
                txtSpeechInput.setText(txtSpeechInput.getText()+" / ");
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueTwo = Float.parseFloat(resultText.getText() + "");

                if (mAddition == true){

                    resultText.setText(mValueOne + mValueTwo +"");
                    mAddition=false;
                }

                if (mSubtract == true){
                    resultText.setText(mValueOne - mValueTwo+"");
                    mSubtract=false;
                }

                if (mMultiplication == true){
                    resultText.setText(mValueOne * mValueTwo+"");
                    mMultiplication=false;
                }

                if (mDivision == true){
                    resultText.setText(mValueOne / mValueTwo+"");
                    mDivision=false;
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult("", false);
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextInInputAndResult(".", true);
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

    //Calculator functions
    private void setTextInInputAndResult(String text, boolean append) {
        setTextInInput(text, append);
        setTextInResult(text, append);
    }

    private void setTextInInput(String text, boolean append) {
        if(append) {
            txtSpeechInput.setText(txtSpeechInput.getText() + text);
        } else {
            txtSpeechInput.setText(text);
        }
    }

    private void setTextInResult(String text, boolean append) {
        if(append) {
            resultText.setText(resultText.getText() + text);
        } else {
            resultText.setText(text);
        }
    }

}