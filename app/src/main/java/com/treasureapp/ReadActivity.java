package com.treasureapp;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ReadActivity extends Activity {

    private NfcAdapter nfcAdapter;
    TextView tview2;

    @Override
    //Show textview
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity);
        tview2 = (TextView)findViewById(R.id.tview2);

        //if nfc not supported
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter == null){
            //print
            Toast.makeText(this,
                    "NFC not supported on this device",
                    Toast.LENGTH_LONG).show();
            //close activity
            finish();
            //nfc not enabled
        }else if(!nfcAdapter.isEnabled()){
            Toast.makeText(this,
                    "NFC not Enabled!",
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    //otherwise if nfc enabled/supported
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String action = intent.getAction();

        //if tag found(held)
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            Toast.makeText(this,
                    "Alert() - tag found!",
                    Toast.LENGTH_SHORT).show();

            //get tag info
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            //if no tag discovered
            if(tag == null){
                Toast.makeText(this,
                        "Alert() - no tag detected",
                        Toast.LENGTH_SHORT).show();
                //display info if tag present
            }else{
                String tagInfo = tag.toString() + "\n";

                //display tag id
                tagInfo += "\nTag Id: \n";
                byte[] tagId = tag.getId();
                tagInfo += "length = " + tagId.length +"\n";
                for(int i=0; i<tagId.length; i++){
                    tagInfo += Integer.toHexString(tagId[i] & 0xFF) + " ";
                }
                tagInfo += "\n";

                //display techlist on tag if nfc technology(tag) discovered
                String[] techList = tag.getTechList();
                tagInfo += "\nTech List\n";
                tagInfo += "length = " + techList.length +"\n";
                for(int i=0; i<techList.length; i++){
                    tagInfo += techList[i] + "\n ";
                }

                //change textview accordingly
                tview2.setText(tagInfo);
            }
        }else{
            Toast.makeText(this,
                    "Alert() : " + action,
                    Toast.LENGTH_SHORT).show();
        }

    }
}