package com.quadeo.wunameme;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends Activity {
	
	private String name1 = "";
	private String name2 = "";
	private int sumFirst = 0;
	private int sumSecond = 0;
	private int sumTotal = 0;
	private String finalName = "";
	private Button wuName;
	private EditText editText1;
	private EditText editText2;
	private TextView textView2;
	private TextView textView3;
	private ImageView tweetBird;
	private boolean nameFound = false;
	private String tweet;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		wuName = (Button) findViewById(R.id.button1); //For button actions
		editText1 = (EditText) findViewById(R.id.editText1); //For User Input Actions
		editText2 = (EditText) findViewById(R.id.editText2); 
		textView2 = (TextView) findViewById(R.id.textView2); //aesthetic purposes
		textView3 = (TextView) findViewById(R.id.textView3); //For altering text in app
		tweetBird = (ImageView) findViewById(R.id.imageView1);
		
		wuName.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v)
			{
				//Resets variables if user wants to enter new name
				name1 = "";
				name2 = "";
				finalName = "";
				sumFirst = 0;
				sumSecond = 0;
				sumTotal = 0;
				nameFound = false;
				tweetBird.setVisibility(0);
				
				InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
			    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
				
				if((editText1.getText().toString()).equalsIgnoreCase("") || (editText2.getText().toString()).equalsIgnoreCase("")) 
				{
					Toast.makeText(getApplicationContext(), "You must enter a first and last name", Toast.LENGTH_LONG).show();
				}
				else 
				{
					name1 = editText1.getText().toString();
					name2 = editText2.getText().toString();
					generateName();
					textView2.setVisibility(1);
				}
			}
		});
		
		tweetBird.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				if(nameFound) 
				{
					tweetMessage();
				}
			}
		});
	}
	
	public void generateName() 
	{
		//Imports names from TextView 
		ArrayList<Character> firstLetters = new ArrayList<Character>();
		ArrayList<Character> lastLetters = new ArrayList<Character>();
		char[] letterMap = new char[56]; //Used later to calculate person's name
		char[] letterMapUpper = new char[57];
								
		//Add each letter to an ArrayList
		for (int i = 0; i<name1.length(); i++) 
		{
			firstLetters.add(name1.charAt(i));
		}
		
		for (int i = 0; i<name2.length(); i++) 
		{
			lastLetters.add(name2.charAt(i));
		}
		
		//Calculate a numerical Value for both names. Last name is dependent on number for first and last name.
		letterMap[0] = 'a';
		letterMap[1] = 'b';
		letterMap[2] = 'c';
		letterMap[3] = 'd';
		letterMap[4] = 'e';
		letterMap[5] = 'f';
		letterMap[6] = 'g';
		letterMap[7] = 'h';
		letterMap[8] = 'i';
		letterMap[9] = 'j';
		letterMap[10] = 'k';
		letterMap[11] = 'l';
		letterMap[12] = 'm';
		letterMap[13] = 'n';
		letterMap[14] = 'o';
		letterMap[15] = 'p';
		letterMap[16] = 'q';
		letterMap[17] = 'r';
		letterMap[18] = 's';
		letterMap[19] = 't';
		letterMap[20] = 'u';
		letterMap[21] = 'v';
		letterMap[22] = 'w';
		letterMap[23] = 'x';
		letterMap[24] = 'y';
		letterMap[25] = 'z';
		letterMap[26] = 'à';
		letterMap[27] = 'á';
		letterMap[28] = 'â';
		letterMap[29] = 'ã';
		letterMap[30] = 'ä';
		letterMap[31] = 'å';
		letterMap[32] = 'æ';
		letterMap[33] = 'ç';
		letterMap[34] = 'è';
		letterMap[35] = 'é';
		letterMap[36] = 'ê';
		letterMap[37] = 'ë';
		letterMap[38] = 'ì';
		letterMap[39] = 'í';
		letterMap[40] = 'î';
		letterMap[41] = 'ï';
		letterMap[42] = 'ð';
		letterMap[43] = 'ñ';
		letterMap[44] = 'ò';
		letterMap[45] = 'ó';
		letterMap[46] = 'ô';
		letterMap[47] = 'õ';
		letterMap[48] = 'ö';
		letterMap[49] = 'ù';
		letterMap[50] = 'ú';
		letterMap[51] = 'û';
		letterMap[52] = 'ü';
		letterMap[53] = 'ý';
		letterMap[54] = 'þ';
		letterMap[55] = 'ÿ';
		
		//Remap numbers + reset [num] on array to fit # of chars
				
		letterMapUpper[0] = 'A';
		letterMapUpper[1] = 'B';
		letterMapUpper[2] = 'C';
		letterMapUpper[3] = 'D';
		letterMapUpper[4] = 'E';
		letterMapUpper[5] = 'F';
		letterMapUpper[6] = 'G';
		letterMapUpper[7] = 'H';
		letterMapUpper[8] = 'I';
		letterMapUpper[9] = 'J';
		letterMapUpper[10] = 'K';
		letterMapUpper[11] = 'L';
		letterMapUpper[12] = 'M';
		letterMapUpper[13] = 'N';
		letterMapUpper[14] = 'O';
		letterMapUpper[15] = 'P';
		letterMapUpper[16] = 'Q';
		letterMapUpper[17] = 'R';
		letterMapUpper[18] = 'S';
		letterMapUpper[19] = 'T';
		letterMapUpper[20] = 'U';
		letterMapUpper[21] = 'V';
		letterMapUpper[22] = 'W';
		letterMapUpper[23] = 'X';
		letterMapUpper[24] = 'Y';
		letterMapUpper[25] = 'Z';
		letterMapUpper[26] = 'À';
		letterMapUpper[27] = 'Á';
		letterMapUpper[28] = 'Â';
		letterMapUpper[29] = 'Ã';
		letterMapUpper[30] = 'Ä';
		letterMapUpper[31] = 'Å';
		letterMapUpper[32] = 'Æ';
		letterMapUpper[33] = 'Ç';
		letterMapUpper[34] = 'È';
		letterMapUpper[35] = 'É';
		letterMapUpper[36] = 'Ê'; 
		letterMapUpper[37] = 'Ë';
		letterMapUpper[38] = 'Ì';
		letterMapUpper[39] = 'Í';
		letterMapUpper[40] = 'Î';
		letterMapUpper[41] = 'Ï';
		letterMapUpper[42] = 'Ð'; 
		letterMapUpper[43] = 'Ñ';
		letterMapUpper[44] = 'Ò';
		letterMapUpper[45] = 'Ó';
		letterMapUpper[46] = 'Ô';
		letterMapUpper[47] = 'Õ';
		letterMapUpper[48] = 'Ö';
		letterMapUpper[49] = 'Ù';
		letterMapUpper[50] = 'Ú';
		letterMapUpper[51] = 'Û';
		letterMapUpper[52] = 'Ü';
		letterMapUpper[53] = 'Ý';
		letterMapUpper[54] = 'Þ';
		letterMapUpper[55] = 'ß';
		letterMapUpper[56] = 'Þ';

		//These two for loops will calculate the first and last name's numerical value
		for(int i = 0; i<name1.length(); i++) 
		{
			for (int j = 0; j<letterMap.length; j++) 
			{
				if((name1.charAt(i))==letterMap[j] || (name1.charAt(i))==letterMapUpper[j])
				{
					sumFirst+=j;
				}
			}
		}
		
		for(int i = 0; i<name2.length(); i++) 
		{
			for (int j = 0; j<letterMap.length; j++) 
			{
				if((name2.charAt(i))==letterMap[j] || (name2.charAt(i))==letterMapUpper[j])
				{
					sumSecond+=j;
				}
			}
		}
		
		sumSecond+=sumFirst; //We need the last name to be dependent on first name and vice versa
		sumTotal = sumSecond;
		
		//Create Arrays to hold all possible First + Last Names
		ArrayList<String> lastName = new ArrayList<String>();
		ArrayList<String> firstName = new ArrayList<String>();
		
		//Add all first names to array. 
		firstName.add("B-Loved");
		firstName.add("Irate");
		firstName.add("Thunderous");
		firstName.add("Ticallion");
		firstName.add("E-Ratic");
		firstName.add("Lucky");
		firstName.add("Bittah");
		firstName.add("Prickly");
		firstName.add("Smilin'");
		firstName.add("Dynamic");
		firstName.add("Zexy");
		firstName.add("Foolish");
		firstName.add("Midnight");
		firstName.add("Drunken");
		firstName.add("Hazy");
		firstName.add("Phantom");
		firstName.add("Crazy");
		firstName.add("Wacko");
		firstName.add("Violent");
		firstName.add("Wicked");
		firstName.add("Scratchin'");
		firstName.add("Mad");
		firstName.add("Master");
		firstName.add("Pesty");
		firstName.add("X-Cessive");
		firstName.add("Amazing");
		firstName.add("Ruff");
		firstName.add("Amateur");
		firstName.add("Unlucky");
		firstName.add("Gentleman");
		firstName.add("Quiet");
		firstName.add("Profound");
		firstName.add("Respected");
		firstName.add("Violent");
		firstName.add("Arrogant");
		firstName.add("Lucky");
		firstName.add("Vulgar");
		firstName.add("Thirty-Sixth");
		firstName.add("Childish");
		firstName.add("Shaolin");
		firstName.add("Bilious Bad");
		firstName.add("Inebriated");
		firstName.add("Radiophonic");
		firstName.add("Curly-Haited");
		firstName.add("Cheeky");
		firstName.add("Ultra-Chronic");
		firstName.add("Illegitimate");
		firstName.add("Visible");
		firstName.add("Intellectual");
		firstName.add("Insane");
		firstName.add("Prince");
		firstName.add("Drunken");
		firstName.add("Winged");
		firstName.add("Unlucky");
		firstName.add("Out-Asthmatic");
		firstName.add("Illmaster");
		firstName.add("Prodigal");
		firstName.add("Ancient");
		firstName.add("Sixty Second");
		firstName.add("Killah");
		firstName.add("Leatha");
		firstName.add("Masta");
		firstName.add("Supreme");
		firstName.add("Inspectah");
		firstName.add("Drunken");
		firstName.add("Babyfaced");
		firstName.add("Buddah");
		firstName.add("Mastah");
		firstName.add("Johnny");
		firstName.add("Chief");
		firstName.add("Fourth");
		firstName.add("Pesty");
		firstName.add("Mighty");
		firstName.add("King");
		firstName.add("Vizual");
		firstName.add("E-Ratic");
		
		//Adding all last names to array. 
		lastName.add("Beggar");
		lastName.add("Tha Gravekeeper");
		lastName.add("Observer");
		lastName.add("Gambino");
		lastName.add("Worlock");
		lastName.add("Ninja");
		lastName.add("Dreamer");
		lastName.add("Mastermind");
		lastName.add("Demon");
		lastName.add("Destroyer");
		lastName.add("Dreamer");
		lastName.add("Madman");
		lastName.add("Bandit");
		lastName.add("Contender");
		lastName.add("Commander");
		lastName.add("Leader");
		lastName.add("Magician");
		lastName.add("General");
		lastName.add("Conqueror");
		lastName.add("Warrior");
		lastName.add("Leader");
		lastName.add("Contender");
		lastName.add("Knight");
		lastName.add("Wanderer");
		lastName.add("Watcher");
		lastName.add("Professional");
		lastName.add("Swami");
		lastName.add("Wizard");
		lastName.add("Lover");
		lastName.add("Madman");
		lastName.add("Pupil");
		lastName.add("Magician");
		lastName.add("Pupil");
		lastName.add("Beggar");
		lastName.add("Demon");
		lastName.add("Dominator");
		lastName.add("Janitah");
		lastName.add("Assistant");
		lastName.add("Gambino");
		lastName.add("Slacker");
		lastName.add("Delinquent");
		lastName.add("Monstah");
		lastName.add("Fundamentalist");
		lastName.add("Choirboy");
		lastName.add("Comedian");
		lastName.add("Terrorist");
		lastName.add("Warlord");
		lastName.add("Killah");
		lastName.add("Samurai");
		lastName.add("The Illfigure");
		lastName.add("Weaponry");
		lastName.add("Ambassador");
		lastName.add("Dragon");
		lastName.add("Disciple");
		lastName.add("Bandit");
		lastName.add("Priestess");
		lastName.add("Rebel");
		lastName.add("Chief");
		lastName.add("Monk");
		lastName.add("Jesus");
		lastName.add("Hands");
		lastName.add("Tha Baptist");
		lastName.add("Tha Grym Reaper");
		lastName.add("Weaponry");
		lastName.add("Tha Darkman");
		lastName.add("Shogun");
		lastName.add("Chamber");
		lastName.add("Stallion");
		lastName.add("Diamonds");
		lastName.add("Tha Commander");
		lastName.add("Professional");
		lastName.add("Mercenary");
		lastName.add("Oddity");
		
		//Assign Name
		while((sumTotal>lastName.size()) && sumTotal>firstName.size()) 
		{
			sumTotal-=lastName.size();
		}
		
		//This will add up first and last names, display them, and set the visibility to true.
		finalName = firstName.get(sumTotal) + " " + lastName.get(sumTotal);
		textView3.setText(finalName); 
		textView3.setVisibility(1);
		nameFound = true;
		tweetBird.setVisibility(1);
	}
	
	public void tweetMessage() //This will give a popup dialog to share this message in twitter when clicked
	{
		tweet = "From this moment forward, I will be known as " + finalName + ". "
				+ "\n -And your Wu-Tang name?"
				+ "\n http://goo.gl/x4uoMS"
				+ "\n via @WuNameMe";
		String tweetUrl = "https://twitter.com/intent/tweet?text=" + tweet + " &url=";
        Uri uri = Uri.parse(tweetUrl);
		startActivity(new Intent(Intent.ACTION_VIEW, uri));
	}
}
