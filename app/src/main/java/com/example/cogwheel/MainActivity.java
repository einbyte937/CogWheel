package com.example.cogwheel;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.android.gms.plus.Plus;

//import static com.google.android.gms.plus.Plus.*;
//import com.google.android.gms.analytics.ExceptionReporter;
//import com.google.android.gms.analytics.HitBuilders;
//import com.google.android.gms.analytics.Tracker;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.PendingResult;
//import com.google.android.gms.drive.Drive;
//import com.google.android.gms.games.Games;
//import com.google.android.gms.games.GamesStatusCodes;
//import com.google.android.gms.games.GamesActivityResultCodes;
//import com.google.android.gms.games.multiplayer.Invitation;
//import com.google.android.gms.games.multiplayer.Multiplayer;
//import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
//import com.google.android.gms.games.multiplayer.Participant;
//import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
//import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
//import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
//import com.google.android.gms.games.multiplayer.realtime.Room;
//import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
//import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
//import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
//import com.google.android.gms.games.snapshot.Snapshot;
//import com.google.android.gms.games.snapshot.SnapshotMetadata;
//import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
//import com.google.android.gms.games.snapshot.Snapshots;
//import com.google.android.gms.plus.Plus;
//import com.google.example.games.basegameutils.BaseGameUtils;
//import com.purplebrain.adbuddiz.sdk.AdBuddiz;

public class MainActivity extends Activity implements
        View.OnClickListener
        //GoogleApiClient.ConnectionCallbacks
        //GoogleApiClient.OnConnectionFailedListener,
        //RealTimeMultiplayer.ReliableMessageSentCallback,
        //RealTimeMessageReceivedListener,
        //RoomStatusUpdateListener,
        //RoomUpdateListener,
        //OnInvitationReceivedListener,
{

    static public GoogleApiClient mGoogleApiClient;

    static public String TAG = MainActivity.class.getSimpleName( );

    public CogWheel s;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent( this, CogWheel.class );

        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        setContentView ( R.layout.main );

        Log.d( TAG, "onCreate( )");
    }

    @Override
    protected void onStart( )
    {
        super.onStart( );

        Log.d( TAG, "onStart( )");
    }

    @Override
    protected void onResume( )
    {

        super.onResume( );

        Log.d( TAG, "onResume( )" );
    }

    @Override
    protected void onPause( )
    {
        super.onPause( );

        unbindService( mConnection );

        Log.d( TAG, "onPause( )" );
    }

    @Override
    protected void onStop( )
    {
        super.onStop( );

        Log.d( TAG, "onStop( )");
    }

    @Override
    protected void onDestroy( )
    {
        super.onDestroy( );

        Log.d( TAG, "onDestroy( )");
    }

    public void gApiClientConnect( )
    {
        mGoogleApiClient = new GoogleApiClient.Builder( this )
                .addConnectionCallbacks( s )
                .addOnConnectionFailedListener( s )
                .addApi( Plus.API )
                .addScope( Plus.SCOPE_PLUS_LOGIN )
                .addApi( Games.API )
                .addScope( Games.SCOPE_GAMES )
                .addApi( Drive.API ).addScope( Drive.SCOPE_APPFOLDER )
                .setViewForPopups( this.getWindow().getDecorView( ).findViewById( R.id.content ) )
                .build();

        try
        {
            mGoogleApiClient.connect( );
        }
        catch( Exception e )
        {
            Log.d( TAG, e.getMessage( ).toString( ));
        }
    }

    private ServiceConnection mConnection = new ServiceConnection( )
    {

        public void onServiceConnected( ComponentName className,
                                        IBinder binder )
        {
            CogWheel.MyBinder b = ( CogWheel.MyBinder ) binder;

            s = b.getService( );

            gApiClientConnect( );

            Log.d(TAG, "onServiceConnected( )");
        }

        public void onServiceDisconnected( ComponentName className )
        {
            s = null;

            Log.d( TAG, "onServiceDisconnected( )");
        }
    };

    @Override
    public void onClick( View v )
    {
        Log.d( TAG, "onClick( )");
    }
}
