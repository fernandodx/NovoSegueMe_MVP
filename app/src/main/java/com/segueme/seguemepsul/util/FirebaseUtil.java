package com.segueme.seguemepsul.util;

import android.os.CountDownTimer;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by FernandoDias on 19/01/17.
 */

public class FirebaseUtil {

    private static FirebaseUtil firebaseUtil;

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference eventos;
    private static DatabaseReference conexaoFire;
    public static boolean isConectado = false;
    private static CountDownTimer timer;

    public static FirebaseUtil getInstance() {
        if(firebaseUtil == null) {
            firebaseUtil = new FirebaseUtil();

            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myConnectionsRef = database.getReference("users/joe/connections");

            final DatabaseReference lastOnlineRef = database.getReference("/users/joe/lastOnline");

            final DatabaseReference connectedRef = database.getReference(".info/connected");
            connectedRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    isConectado = snapshot.getValue(Boolean.class);
                    if (isConectado) {
                        DatabaseReference con = myConnectionsRef.push();
                        con.setValue(Boolean.TRUE);

                        con.onDisconnect().removeValue();

                        lastOnlineRef.onDisconnect().setValue(ServerValue.TIMESTAMP);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    System.err.println("Listener was cancelled at .info/connected");
                }
            });
        }

        return firebaseUtil;
    }

    public DatabaseReference getEventos() {
        if(eventos == null) {
            eventos = firebaseDatabase.getReference("/EVENTOS");
        }

        return eventos;
    }

    public static void verificarConexaoTimeOut(final ConexaoFirebaseListener conexaoFirebaseListener) {

        //30 segundos
        timer = new CountDownTimer(30000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                if(conexaoFirebaseListener != null) {
                    conexaoFirebaseListener.timeOut(isConectado);
                }
            }
        };

        timer.start();

    }

    public static void stopTimeOut() {
        timer.cancel();
    }

    public static interface ConexaoFirebaseListener {

        void timeOut(boolean isConectado);

    }



}
