package com.ctwiiter.ctwitterbackend.configuration;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
public class MailConfiguration {

    private static final String APPLICATION_NAME =  "Ctwitter";
    private static final JsonFactory JSON_FACTORY  = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_SEND);

    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    private Credential getCredentials(final HttpTransport HTTP_TRANSPORT) throws IOException{

        InputStream in = MailConfiguration.class.getResourceAsStream(CREDENTIALS_FILE_PATH);

        if (in == null){
            throw new FileNotFoundException("Credential file not found ");
        }
        GoogleClientSecrets googleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                googleClientSecrets, SCOPES).setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH))).setAccessType("offline").build();

        LocalServerReceiver localServerReceiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential =     new AuthorizationCodeInstalledApp(flow, localServerReceiver).authorize("user");
        return credential;
        // what isthe meant of the lifn

    }

    @Bean
    public Gmail getService(){
        NetHttpTransport HTTP_TRANSPORT;

        try{
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).
                    setApplicationName(APPLICATION_NAME).
                        build();

        } catch (GeneralSecurityException  | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
