package com.example.android.orthodonticseducationalquiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    TextView qNo;
    TextView q1Vu;
    RadioButton rB1, rB2, rB3, rB4;
    TextView cS, cSL;
    RadioGroup rG;
    EditText sWd;
    LinearLayout qALayout;
    CheckBox chkCh1, chkCh2, chkCh3, chkCh4;
    Button fA, nXT, eXT,bYE;


    ArrayList<String> question;
    ArrayList<ArrayList<String>> answer;
    ArrayList<String> answer1List, answer2List, answer3List, answer4List, answer5List, answer6List;

    int screenTotal;
    int screenNext = 0;
    int userScore = 0;
    String sWord;
    boolean next = false;
    int actionId;
    int currentQuestion=0;

    /**
     * If the user has clicked the FINAL ANSWER button, set q{number}FinalAnswer variable to true
     **/
    boolean q1FinalAnswer = false;
    boolean q2FinalAnswer = false;
    boolean q3FinalAnswer = false;
    boolean q4FinalAnswer = false;
    boolean q5FinalAnswer = false;
    boolean q6FinalAnswer = false;

    /**
     * q{number}AnswerKey variables will hold the position of the correct choice of answer
     * in the answer arrayList.
     ***/
    int q1AnswerKey = 0;
    int q2AnswerKey = 3;
    int q3AnswerKey = 0;
    int q4AnswerKey = 3;
    String q5AnswerKey = "RETAINER";
    int q6AnswerKey;
    int finalScreen = 100;  /** Just setting a outlier number as a reference point**/

    /** Declare state of checkbox states **/
    boolean choice1State = false;
    boolean choice2State = false;
    boolean choice3State = false;
    boolean choice4State = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    qNo = (TextView) findViewById(R.id.querynumber_view);
    q1Vu =(TextView) findViewById(R.id.query1_text_view);
    rB1 = (RadioButton)findViewById(R.id.answerChoice1);
    rB2 = (RadioButton)findViewById(R.id.answerChoice2);
    rB3 = (RadioButton)findViewById(R.id.answerChoice3);
    rB4 = (RadioButton)findViewById(R.id.answerChoice4);
    cS = (TextView) findViewById(R.id.currentScore);
    cSL = (TextView) findViewById(R.id.currentScoreLabel);
    rG =(RadioGroup) findViewById(R.id.rgAnswerGroup);
    qALayout =(LinearLayout) findViewById(R.id.questionAnswerLayout);
    sWd =(EditText) findViewById(R.id.scrambleWord_View);
    chkCh1 =(CheckBox) findViewById(R.id.chkChoice1);
    chkCh2 =(CheckBox) findViewById(R.id.chkChoice2);
    chkCh3 =(CheckBox) findViewById(R.id.chkChoice3);
    chkCh4 =(CheckBox) findViewById(R.id.chkChoice4);
    bYE = (Button) (findViewById(R.id.byeButtonView));
    fA = (Button) findViewById(R.id.finalAnswerButtonView);
    nXT = (Button) findViewById(R.id.nextButtonView);
    eXT = (Button) findViewById(R.id.exitButtonView);

        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getInt("currentQuestionState",0);
            if (currentQuestion != finalScreen) {
                userScore = savedInstanceState.getInt("userScoreState", 0);
                screenNext = savedInstanceState.getInt("screenNextState", 0);
                cS.setText(String.valueOf(userScore));
            }

        }


        /**Declare an ArrayList to maintain a list of questions**/
    question =new ArrayList<String>();

    /**Declare an Array of ArrayList to maintain an Array of answerchoice for each answer**/
    answer =new ArrayList<ArrayList<String>>();

    /***Initialize ArrayList for the answer options of each question**/
    answer1List =new ArrayList<String>();
    answer2List =new ArrayList<String>();
    answer3List =new ArrayList<String>();
    answer4List =new ArrayList<String>();
    answer5List =new ArrayList<String>();
    answer6List =new ArrayList<String>();

    /**Add the questions to an arraylist of Strings **/
        question.add("A dentist who corrects and moves teeth into ideal position and aligns how they bite together is called as ");
        question.add("Braces and Orthodontic treatment are suitable for");
        question.add("The first orthodontic consultation for prescreening in children should be no later than this age");
        question.add("A patient with Braces should avoid all of the following except:");
        question.add("Try unscrambling the given word ");
        question.add("Conditions which may be "+
                "treated by Orthodontic treatment are : ");

    /**Add answer options for question1 **/
        answer1List.add("Orthodontist");
        answer1List.add("Pediatric Dentist");
        answer1List.add("General Dentist");
        answer1List.add("Orthopaedic");
        answer.add(answer1List);

    /**Add answer options for question2**/
        answer2List.add("Children");
        answer2List.add("Teens");
        answer2List.add("Adults");
        answer2List.add("All of the above");
        answer.add(answer2List);


    /**Add answer options for question3**/
        answer3List.add("7");
        answer3List.add("12");
        answer3List.add("10");
        answer3List.add("5");
        answer.add(answer3List);


    /**Add answer options for question4**/
        answer4List.add("Nuts and Seeds");
        answer4List.add("Sticky and hard candy");
        answer4List.add("Whole grains and hard rolls");
        answer4List.add("Soft vegetables and soft fruits, soft food items");
        answer.add(answer4List);

    /**Add answer options for question4**/
        answer5List.add("RTEAIREN");
        answer5List.add("KCRABSET");
        answer5List.add("REWIHCRA");
        answer5List.add("SURETIGAL");
        answer.add(answer5List);

    /***Add answer options for question4**/
        answer6List.add("Speech Impediments");
        answer6List.add("Jaw or TMJ pain");
        answer6List.add("Difficulty chewing and eating");
        answer6List.add("Gum disease and tooth decay");
        answer.add(answer6List);


    /**Set screenTotal to total number of questions**/
    screenTotal =question.size()-1;

    /**
     * Call setValues method to setup the screen for the first question and thereafter
     **/
    bYE.setVisibility(View.INVISIBLE);
    setValues(currentQuestion);

    /**Setup actionlistener for the scrambleWord question's EditText View**/
        sWd.setOnEditorActionListener(new  OnEditorActionListener() {

        @Override
        public boolean onEditorAction (TextView v,int actionId, KeyEvent event){
            String input;
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                input = v.getText().toString();
                checkScrambleAnswer(input);
                hideKeyboard(sWd);
                return true;

            }
            return false;
        }

    });


}
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("userScoreState", userScore);
        savedInstanceState.putInt("screenNextState", screenNext);
        savedInstanceState.putInt("currentQuestionState", currentQuestion);
    }

    /**
     * Hide the keyboard
     **/
    private void hideKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * Set initial choices for radio buttons for questions 1-4.  Set initial scramble word for question 5.
     **/
    public void setValues(int i) {
        String message, sWord;
        if (currentQuestion == finalScreen)
        {
            displayresults();
        }
        else {
                 currentQuestion = i;
             if (i == 4) {
                clearRButtons();
                chkCh1.setVisibility(View.INVISIBLE);
                chkCh2.setVisibility(View.INVISIBLE);
                chkCh3.setVisibility(View.INVISIBLE);
                chkCh4.setVisibility(View.INVISIBLE);
                sWd.setVisibility(View.VISIBLE);
                qNo.setText(String.valueOf(i + 1));
                message = question.get(4);
                sWord = "TRAEINER";
                message = message.concat(sWord);
                q1Vu.setText(message);

                } else if (i == 5) {
                clearRButtons();
                sWd.setVisibility(View.INVISIBLE);
                chkCh1.setVisibility(View.VISIBLE);
                chkCh2.setVisibility(View.VISIBLE);
                chkCh3.setVisibility(View.VISIBLE);
                chkCh4.setVisibility(View.VISIBLE);

                qNo.setText(String.valueOf(i + 1));
                message = question.get(5);
                q1Vu.setText(message);
                chkCh1.setText(answer.get(i).get(0));
                chkCh2.setText(answer.get(i).get(1));
                chkCh3.setText(answer.get(i).get(2));
                chkCh4.setText(answer.get(i).get(3));
                next = false;
             } else {
                qNo.setText("");
                q1Vu.setText("");
                chkCh1.setVisibility(View.INVISIBLE);
                chkCh2.setVisibility(View.INVISIBLE);
                chkCh3.setVisibility(View.INVISIBLE);
                chkCh4.setVisibility(View.INVISIBLE);
                sWd.setVisibility(View.INVISIBLE);
                qNo.setText(String.valueOf(i + 1));
                q1Vu.setText(question.get(i));
                rB1.setText(answer.get(i).get(0));
                rB2.setText(answer.get(i).get(1));
                rB3.setText(answer.get(i).get(2));
                rB4.setText(answer.get(i).get(3));

                next = false;
                }
        }
    }


    /**
     * For each question from 0 to 6 verify the answer, increase score, display
     * correct answer if the  user's answer is incorrect
     **/

    public void checkAnswer(View view) {
        String message;

        switch (screenNext) {


            case 0:
                if (rB1.isChecked() && (!q1FinalAnswer)) {
                    userScore = userScore + 1;
                    q1FinalAnswer = true;
                } else {
                    message = "The correct answer is ";
                    message = message.concat(answer1List.get(q1AnswerKey));

                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 20, 230);
                    toast.show();
                }
                break;
            case 1:
                if (rB4.isChecked() && (!q2FinalAnswer)) {
                    userScore = userScore + 1;
                    q2FinalAnswer = true;
                } else {
                    message = "The correct answer is ";
                    message = message.concat(answer2List.get(q2AnswerKey));
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 20, 230);
                    toast.show();
                }

                break;

            case 2:
                if (rB1.isChecked() && (!q3FinalAnswer)) {
                    userScore = userScore + 1;
                    q3FinalAnswer = true;
                } else {
                    message = "The correct answer is ";
                    message = message.concat(answer3List.get(q3AnswerKey));
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 20, 230);
                    toast.show();
                }

                break;
            case 3:
                if (rB4.isChecked() && (!q4FinalAnswer)) {
                    userScore = userScore + 1;
                    q4FinalAnswer = true;
                } else {
                    message = "The correct answer is ";
                    message = message.concat(answer4List.get(q4AnswerKey));
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 20, 230);
                    toast.show();
                }

                break;

            case 4:
                if ((q5AnswerKey.equalsIgnoreCase(sWord)) && (!q5FinalAnswer)) {
                    userScore = userScore + 1;
                    q5FinalAnswer = true;
                } else {
                    message = "The correct answer is ";
                    message = message.concat(q5AnswerKey);
                    Toast toast1 = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.TOP, 20, 230);
                    toast1.show();
                }

                break;

            case 5:
                if ((choice1State && choice2State && choice3State && choice4State) && (!q6FinalAnswer)) {
                    userScore = userScore + 1;
                    q6FinalAnswer = true;
                } else {
                    message = "All the choices are correct.  Apart from these, " +
                            "Sleep apnea caused by mouth breathing and snoring, Grinding or clenching of"
                            +"\n"+ "teeth can also be corrected by Orthodontics";
                    Toast toast1 = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                    toast1.setGravity(Gravity.TOP, 20, 230);
                    toast1.show();
                }
                break;

            default:
                message = "BYE !! PLEASE RE-START THE QUIZ";
                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 20, 230);
                toast.show();
                break;
        }

        cS.setText(String.valueOf(userScore));

    }

    /**
     * Verify answer for the Scramble word question
     **/
    public void checkScrambleAnswer(String input) {

        sWord = input.toUpperCase();
        checkAnswer(sWd);
    }

    /**
     * Ask the user to confirm answer. User has to press FINAL ANSWER button in response
     **/
    public void askToConfirm(View view) {
        String message;

        message = "Submit Final Answer ?";
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 20, 230);
        toast.show();

    }

    /**
     * Confirm the user's answer by checking response***/
    public void confirmAnswer(View view)
    {
        checkAnswer(view);
    }

    /*** Move to the next screen  **/
    public void moveToNext(View view) {

        next = true;
        rG.clearCheck();

        if (screenNext == screenTotal) {
            displayresults();
        } else {
            screenNext += 1;
            setValues(screenNext);
        }

    }

    /**
     * Clear the radiobuttons and make the radioGroup invisible from the layout**/
    public void clearRButtons() {
        qNo.setText("");
        q1Vu.setText("");
        rB1.setText("");
        rB1.setVisibility(View.INVISIBLE);
        rB2.setText("");
        rB2.setVisibility(View.INVISIBLE);
        rB3.setText("");
        rB3.setVisibility(View.INVISIBLE);
        rB4.setText("");
        rB4.setVisibility(View.INVISIBLE);
        rG.clearCheck();

    }

    /** If checkbox 1 is checked, store its state.**/
    public void setChkChoice1Status(View view) {

        choice1State = chkCh1.isChecked();

    }
    /** If checkbox 2 is checked, store its state.**/
    public void setChkChoice2Status(View view) {

        choice2State = chkCh2.isChecked();

    }
    /** If checkbox 3 is checked, store its state.**/
    public void setChkChoice3Status(View view) {

        choice3State = chkCh3.isChecked();

    }
    /** If checkbox 4 is checked, store its state.**/

    public void setChkChoice4Status(View view) {

        choice4State = chkCh4.isChecked();

    }

    /**
     * Display final results and good bye message
     **/
    public void displayresults() {

        currentQuestion = finalScreen;
        clearRButtons();
        sWd.setVisibility(View.INVISIBLE);
        chkCh4.setVisibility(View.INVISIBLE);
        chkCh3.setVisibility(View.INVISIBLE);
        chkCh2.setVisibility(View.INVISIBLE);
        chkCh1.setVisibility(View.INVISIBLE);


        fA.setVisibility(View.INVISIBLE);
        nXT.setVisibility(View.INVISIBLE);
        cS.setVisibility(View.INVISIBLE);
        cSL.setVisibility(View.INVISIBLE);
        eXT.setVisibility(View.INVISIBLE);

        bYE.setVisibility(View.VISIBLE);
        TextView TV = (TextView) (findViewById(R.id.question_label_view));
        TV.setVisibility(View.INVISIBLE);
        String message = "CONGRATULATIONS !" + "\n" + "\n"
                + "You completed the quiz.  " +
                "\n" + "\n" + "We wish you a healthy set of teeth" +
                "\n" + "and a great smile. " +
                "\n" + "\n" + "Bye !";
        q1Vu.setText(message);


    }

    /**
     * Exit the quiz by closing the app
     **/
    public void closeQuiz(View view) {
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
