package com.example.cogwheel;

import android.app.Service;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

public class CogWheel extends Service
implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
{

    public String TAG = CogWheel.class.getSimpleName( );

    static public boolean mIsResolving = false;

    static public boolean mShouldResolve = false;

    private static final int RC_SIGN_IN = 0;

    public class MyBinder extends Binder
    {
        CogWheel getService( )
        {
            return CogWheel.this;
        }
    }

    private final IBinder mBinder = new MyBinder ( );

    @Override
    public void onCreate( )
    {
        super.onCreate( );

        Log.d( TAG, "onCreate( )");
    }

    @Override
    public int onStartCommand( Intent intent, int flags, int startId )
    {
        Log.d( TAG, "onStartCommand( )" );

        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind( Intent intent )
    {
        return mBinder;
    }

    @Override
    public void onConnected( Bundle bundle )
    {
        Log.d( TAG, "onConnected( )");
    }

    @Override
    public void onConnectionSuspended( int i )
    {
        Log.d( TAG, "onConnectionSuspended( )" );
    }

    @Override
    public void onConnectionFailed( ConnectionResult connectionResult )
    {
        Log.d( TAG, "onConnectionFailed:" + connectionResult );

        if ( !mIsResolving && mShouldResolve ) {
            if ( connectionResult.hasResolution( ) ) {
                try {
                    connectionResult.startResolutionForResult( MainActivity.activity, RC_SIGN_IN );
                    mIsResolving = true;
                } catch ( IntentSender.SendIntentException e ) {
                    Log.e( TAG, "Could not resolve ConnectionResult.", e );
                    mIsResolving = false;
                    MainActivity.mGoogleApiClient.connect( );
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                //showErrorDialog(connectionResult);
            }
        } else {
            // Show the signed-out UI
            //showSignedOutUI();
        }
        Log.d( TAG, "onConnectionFailed( )" );
    }
}
