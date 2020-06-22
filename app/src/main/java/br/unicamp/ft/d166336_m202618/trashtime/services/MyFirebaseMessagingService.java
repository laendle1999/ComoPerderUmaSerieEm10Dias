package br.unicamp.ft.d166336_m202618.trashtime.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onNewToken(String s){
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        System.out.println(("SERVICE --> Mensagem chegou"));

        if (remoteMessage.getNotification() != null){
            System.out.println("SERVICE -->"+remoteMessage.getNotification().getBody());
        }
        if(remoteMessage.getData().size() > 0){
            System.out.println("SERVICE --> DADOS: "+remoteMessage.getData());
        }
    }

}