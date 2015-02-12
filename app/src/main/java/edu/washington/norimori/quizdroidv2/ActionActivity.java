package edu.washington.norimori.quizdroidv2;

import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ActionActivity extends ActionBarActivity {

    private String chosenTopicName; //Name of chosen topic from list
    private List<Topic> allTopics; //ArrayList of all available Topic objects
    private int indexOfCurrQ = 0; //Keep track of current Question
    private int totalCorrQ = 0; //Keep track of number of correctly answered questions

    private RadioGroup radioGroup; //Question choices for current question
    private Button btnSubmit; //Submit answer to proceed to Answer Summary fragment
    private Question currQuestion; //Current question during quiz
    private Topic topic; //Topic object of chosen quiz
    private int radioId; //Chosen radio button for one question

    private Button btnAction; //"Next" or "Finish" action during quiz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        //Display initial Topic Overview fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new topicOverviewFragment())
                .commit();
        }

        //Retrieve chosen topic name
        Intent launchedMe = getIntent();
        chosenTopicName = launchedMe.getStringExtra("chosenTopic");

        //Create all Topics and Question objects
        String[] PokemonQ1Choices = {"Electric", "Psychic", "Water", "Fire"};
        Question PokemonQ1 = new Question("What type is Pikachu?", PokemonQ1Choices, 0);
        String[] PokemonQ2Choices = {"Steel", "Normal", "Water", "Bug"};
        Question PokemonQ2 = new Question("What type is Jigglypuff?", PokemonQ2Choices, 1);
        String[] PokemonQ3Choices = {"Leaf", "Dark", "Ghost", "Ice"};
        Question PokemonQ3 = new Question("What type is Glaceon?", PokemonQ3Choices, 3);
        Question[] PokemonQuestions = {PokemonQ1, PokemonQ2, PokemonQ3};
        Topic Pokemon = new Topic("Pokemon", "Gotta catch'em all!", PokemonQuestions, 3);

        String[] MathQ1Choices = {"5", "1", "3", "2"};
        Question MathQ1 = new Question("1 + 1 = ?", MathQ1Choices, 3);
        String[] MathQ2Choices = {"0", "1", "9000", "90000"};
        Question MathQ2 = new Question("9000 x 0 = ?", MathQ2Choices, 0);
        String[] MathQ3Choices = {"0", "55", "10", "-10"};
        Question MathQ3 = new Question("5 + 5 = ?", MathQ3Choices, 2);
        String[] MathQ4Choices = {"-3", "107", "3", "17"};
        Question MathQ4 = new Question("10 - 7 = ?", MathQ4Choices, 2);
        String[] MathQ5Choices = {"21", "12", "75", "-57"};
        Question MathQ5 = new Question("7 + 5", MathQ5Choices, 1);
        Question[] MathQuestions = {MathQ1, MathQ2, MathQ3, MathQ4, MathQ5};
        Topic Math = new Topic("Math", "MAAAAAAAATH", MathQuestions, 5);

        String[] PhysicsQ1Choices = {"Charmander", "Mikasa", "Jeffery", "Isaac Newton"};
        Question PhysicsQ1 = new Question("What famous dude had an apple fall on his head?", PhysicsQ1Choices, 3);
        String[] PhysicsQ2Choices = {"March 14th, 1879", "Today", "Christmas", "Next year"};
        Question PhysicsQ2 = new Question("When was Albert Einsten Born?", PhysicsQ2Choices, 0);
        String[] PhysicsQ3Choices = {"FALSE", "NO", "9.807m/s^2", "yes"};
        Question PhysicsQ3 = new Question("How much is gravity on Earth?", PhysicsQ3Choices, 2);
        String[] PhysicsQ4Choices = {"atmosphereic pressure/weight", "density/volume", "distance/time", "your enthusiasm!"};
        Question PhysicsQ4 = new Question("How do you calculate speed?", PhysicsQ4Choices, 2);
        String[] PhysicsQ5Choices = {"PIE", "999.97 kg/m^3", "-5", "0"};
        Question PhysicsQ5 = new Question("What is the density of water?", PhysicsQ5Choices, 1);
        String[] PhysicsQ6Choices = {"-50F", "100C", "infinty", "never"};
        Question PhysicsQ6 = new Question("What is the boiling point of water?", PhysicsQ6Choices, 1);
        Question[] PhysicsQuestions = {PhysicsQ1, PhysicsQ2, PhysicsQ3, PhysicsQ4, PhysicsQ5, PhysicsQ6};
        Topic Physics = new Topic("Physics", "IT'S EVERYWHERE ∩(ﾟ∀ﾟ∩)", PhysicsQuestions, 6);

        String[] MarvelSuperHeroesQ1Choices = {"Arhcer", "Ironman", "Hulk", "Spiderman"};
        Question MarvelSuperHeroesQ1 = new Question("Who is the strongest Marvel super hero", MarvelSuperHeroesQ1Choices, 2);
        String[] MarvelSuperHeroesQ2Choices = {"Wolverine", "Professor X", "Iceman", "Beast"};
        Question MarvelSuperHeroesQ2 = new Question("Who was not in the X-Men originally?", MarvelSuperHeroesQ2Choices, 0);
        String[] MarvelSuperHeroesQ3Choices = {"Pikachu", "The Prince", "Charles Francis Xavier", "Billy Bob Joe"};
        Question MarvelSuperHeroesQ3 = new Question("What is Professor X's full name?", MarvelSuperHeroesQ3Choices, 2);
        String[] MarvelSuperHeroesQ4Choices = {"Spongebob", "Patrick", "Robert Louis Drake", "Jim"};
        Question MarvelSuperHeroesQ4 = new Question("What is Iceman's real name?", MarvelSuperHeroesQ4Choices, 2);
        String[] MarvelSuperHeroesQ5Choices = {"rainbow", "blue", "orange", "pink"};
        Question MarvelSuperHeroesQ5 = new Question("What is the color of Beast's fur?", MarvelSuperHeroesQ5Choices, 1);
        Question[] MarvelSuperHeroesQuestions = {MarvelSuperHeroesQ1, MarvelSuperHeroesQ2, MarvelSuperHeroesQ3, MarvelSuperHeroesQ4, MarvelSuperHeroesQ5};
        Topic MarvelSuperHeroes = new Topic("Marvel Super Heroes", "POW POOOOOWWWWWWW", MarvelSuperHeroesQuestions, 5);

        //Populate ArrayList with Topics
        allTopics = new ArrayList<Topic>();
        allTopics.add(Pokemon);
        allTopics.add(Math);
        allTopics.add(Physics);
        allTopics.add(MarvelSuperHeroes);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action, menu);
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

    //Adjust visiblity of "Submit" button when radio button is selected
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            btnSubmit.setVisibility(btnSubmit.VISIBLE);
        } else {
            btnSubmit.setVisibility(btnSubmit.INVISIBLE);
        }
    }

    //Returns true if all questions have been answered.
    public boolean finished() {
        return indexOfCurrQ == (topic.getTotalQ());
    }

    //Fragment displaying quiz topic overview.
    public class topicOverviewFragment extends Fragment {

        public topicOverviewFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_topicoverview, container, false);

            //Display chosen topic overview
            TextView topicName = (TextView) rootView.findViewById(R.id.topic);
            TextView desc = (TextView) rootView.findViewById(R.id.desc);
            TextView totalQ = (TextView) rootView.findViewById(R.id.totalQ);
            for (int i = 0; i < allTopics.size(); i++) {
                if (allTopics.get(i).getName().equals(chosenTopicName)) {
                    topic = allTopics.get(i);
                    topicName.setText(allTopics.get(i).getName());
                    desc.setText("Topic Description: " + allTopics.get(i).getDescription());
                    totalQ.setText("Total Number of Questions: " + allTopics.get(i).getTotalQ());
                }
            }

            //Transition to first question in quiz
            Button btnBegin = (Button) rootView.findViewById(R.id.btnBegin);
            btnBegin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new questionFragment())
                        .commit();
                }
            });

            return rootView;
        }
    }

    //Fragment displaying single question.
    public class questionFragment extends Fragment {

        public questionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_question, container, false);

            //Make "Submit" button invisible when no question answer is selected.
            btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
            btnSubmit.setVisibility(btnSubmit.INVISIBLE);

            //Display current question
            TextView qText = (TextView) rootView.findViewById(R.id.qText);
            Question[] questions = topic.getQuestions();
            currQuestion = questions[indexOfCurrQ]; //Get current question player is on
            qText.setText(currQuestion.getqText());

            //Display current question's choices
            String[] possibleA = currQuestion.getPossibleA();
            RadioButton option1 = (RadioButton) rootView.findViewById(R.id.option1);
            option1.setText(possibleA[0]);
            RadioButton option2 = (RadioButton) rootView.findViewById(R.id.option2);
            option2.setText(possibleA[1]);
            RadioButton option3 = (RadioButton)rootView.findViewById(R.id.option3);
            option3.setText(possibleA[2]);
            RadioButton option4 = (RadioButton) rootView.findViewById(R.id.option4);
            option4.setText(possibleA[3]);

            //Transition to Answer Summary of current question
            radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
            Button btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(radioGroup.getCheckedRadioButtonId() != -1) { //If any radio button is checked
                        int id= radioGroup.getCheckedRadioButtonId();
                        View radioButton = radioGroup.findViewById(id);
                        radioId = radioGroup.indexOfChild(radioButton);
                        getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new answerSummaryFragment())
                            .commit();
                    }
                }
            });

            return rootView;
        }

    }

    //Fragment displaying single question's summary.
    public class answerSummaryFragment extends Fragment {

        public answerSummaryFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_answersummary, container, false);

            //Display user's chosen answer
            TextView chosenA = (TextView) rootView.findViewById(R.id.chosenA);
            chosenA.setText("You chose: " + currQuestion.getChosenA(radioId));

            //Display correct answer and corresponding message
            TextView correctA = (TextView) rootView.findViewById(R.id.correctA);
            if (currQuestion.indexOfCorrA == radioId) { //If the chosen answer is correct
                correctA.setText("You're right! The correct answer is: " + currQuestion.getCorrAText());
                totalCorrQ++;
            } else {
                correctA.setText("Nope. The correct answer is: " + currQuestion.getCorrAText());
            }
            indexOfCurrQ++;

            TextView score = (TextView) rootView.findViewById(R.id.score);
            score.setText("You have answered " + totalCorrQ + " out of " + topic.getTotalQ() + " correct.");

            //Next button to go to the next question (or Finish button to end quiz)
            btnAction = (Button) rootView.findViewById(R.id.action);
            if (finished()) {
                btnAction.setText("Finish!");
            } else {
                btnAction.setText("Next");
            }

            //Execute action based on quiz progress. Go to next question or finish quiz.
            btnAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finished()) {
                        Intent nextActivity = new Intent(ActionActivity.this, MainActivity.class);
                        startActivity(nextActivity);
                    } else {
                        getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new questionFragment())
                            .commit();
                    }
                }
            });

            return rootView;
        }
    }

}
