package com.akhil.sheets;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class SheetsServiceUtil {
    private static final String APPLICATION_NAME = "Backend Challenge Daisy";

    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = GoogleAuthorizeUtil.authorize();

        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
          GsonFactory.getDefaultInstance(), credential).
                setApplicationName(APPLICATION_NAME).build();
    }


}