package com.example.varunelango.contacts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ST;
        ST = (EditText)findViewById(R.id.SearchText);

        final String[] Names = new String[]{"Harry Potter", "Hermoine Granger", "Ronald Weasley", "Draco Malfoy", "Snape", "Dumbledore", "Hagrid","Sirius","Voldemort","Bellatrix"};
        final int Image[] = new int[]{R.mipmap.harry,R.mipmap.hermoine,R.mipmap.ron,R.mipmap.malfoy,R.mipmap.snape,R.mipmap.dumbledore,R.mipmap.hagrid,R.mipmap.sirius,R.mipmap.voldemort,R.mipmap.bellatrix};

        final String[] NTemp = new String[1];
        final int[] iTemp = new int[1];

        final int[] Sflag = {0};

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String, String>>();

        for(int i=0; i<10; i++){
            HashMap<String,String> hm = new HashMap<String,String>();
            hm.put("Name", Names[i]);
            hm.put("Image", Integer.toString(Image[i]));
            aList.add(hm);
        }

        String[] from = {"Image","Name" };
        int[] to = { R.id.ContactImage, R.id.ContactText };
        SimpleAdapter nameAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.custom_row, from, to);
        ListView CLV = (ListView) findViewById(R.id.ContactsListView);
        CLV.setAdapter(nameAdapter);

        final ToggleButton TSB = (ToggleButton) findViewById(R.id.ToggleSortButton);
        TSB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (Sflag[0] == 0) {
                        int a, b;
                        for (a=0;a<10;a++)
                            for (b=0;b<9-a;b++)
                                if (Names[b].compareTo(Names[b + 1]) > 0) {
                                    NTemp[0] = Names[b];
                                    Names[b] = Names[b+1];
                                    Names[b+1] = NTemp[0];

                                    iTemp[0] = Image[b];
                                    Image[b] = Image[b + 1];
                                    Image[b+1] = iTemp[0];
                                }
                        Sflag[0]=1;
                    }


                        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

                        for (int i=0;i<10;i++) {
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("Name", Names[i]);
                            hm.put("Image", Integer.toString(Image[i]));
                            aList.add(hm);
                        }

                        String[] from = {"Image", "Name"};
                        int[] to = {R.id.ContactImage, R.id.ContactText};
                        SimpleAdapter nameAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.custom_row, from, to);
                        ListView CLV = (ListView) findViewById(R.id.ContactsListView);
                        CLV.setAdapter(nameAdapter);
                }

                else {

                        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

                        for (int i=9;i>=0;i--) {
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("Name", Names[i]);
                            hm.put("Image", Integer.toString(Image[i]));
                            aList.add(hm);
                        }

                        String[] from = {"Image", "Name"};
                        int[] to = {R.id.ContactImage, R.id.ContactText};
                        SimpleAdapter nameAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.custom_row, from, to);
                        ListView CLV = (ListView) findViewById(R.id.ContactsListView);
                        CLV.setAdapter(nameAdapter);
                }
            }
        });

        Button SB = (Button) findViewById(R.id.SearchButton);
        SB.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick (View v)
                    {
                        String SearchText = ST.getText().toString();
                        int i=0,flag=0;
                        for(;i<10;i++)
                        {
                            if(SearchText.equalsIgnoreCase(Names[i])) {
                                Toast.makeText(MainActivity.this, "Contact Found: " + SearchText, Toast.LENGTH_LONG).show();
                                flag=1;
                            }
                        }
                        if(flag==0)
                        {
                            Toast.makeText(MainActivity.this, "Missing", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
