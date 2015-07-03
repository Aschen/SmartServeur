package com.aschen.smartserveur.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aschen.smartserveur.R;
import com.aschen.smartserveur.model.Session;
import com.aschen.smartserveur.service.SessionService;
import com.aschen.smartserveur.tools.DataHolder;
import com.aschen.smartserveur.tools.ZXingScannerView;
import com.google.zxing.Result;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FlashCodeActivity extends ActionBarActivity implements ZXingScannerView.ResultHandler
{
    private ZXingScannerView mScannerView;
    private final String TAG = "ScanActivity";


    private SessionService _sessionService;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view


        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(SessionService.URL_API)
                .build();

        _sessionService = adapter.create(SessionService.class);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult)
    {
        try
        {
            int tableId = Integer.parseInt(rawResult.getText().toString());
            _sessionService.createSession(new Session(false, tableId), new Callback<Session>()
            {
                @Override
                public void success(Session session, Response response)
                {
                    Intent  accueil = new Intent(FlashCodeActivity.this, AccueilTableActivity.class);

                    DataHolder.getInstance().sessionId(session.id());

                    startActivity(accueil);
                }

                @Override
                public void failure(RetrofitError error)
                {
                    Toast.makeText(getApplicationContext(), "Failed : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(FlashCodeActivity.this, "QRCODE invalide", Toast.LENGTH_SHORT).show();
        }
     }

    public void Suite(View v)
    {
        _sessionService.createSession(new Session(false, 1), new Callback<Session>()
        {
            @Override
            public void success(Session session, Response response)
            {
                Intent  flasher = new Intent(FlashCodeActivity.this, AccueilTableActivity.class);

                DataHolder.getInstance().sessionId(session.id());

                startActivity(flasher);
            }

            @Override
            public void failure(RetrofitError error)
            {
                Toast.makeText(getApplicationContext(), "Failed : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flash_code, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
