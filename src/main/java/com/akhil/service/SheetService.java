package com.akhil.service;

import com.akhil.entity.User;
import com.akhil.service.impl.UserServiceImpl;
import com.akhil.sheets.SheetsServiceUtil;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SheetService {

    String spreadSheetId = "1O_qmsoVYaQaav4BRCSzbvEE8-vXulZuS3Al7-Z_zyAQ";

    @Autowired
    private SheetsServiceUtil sheetsServiceUtil;

    @Autowired
    private UserServiceImpl userService;

    public void writeInSheet() throws GeneralSecurityException, IOException {
        //sheetsServiceUtil;
        this.clearSheet();
        this.writeHeading();

        List<User> userList = userService.getAllUsers();
        List<List<Object>> value = new ArrayList<>();
        int size = userList.size();
        List<Object> id = new ArrayList<>();
        List<Object> name = new ArrayList<>();
        List<Object> income = new ArrayList<>();
        List<Object> savings = new ArrayList<>();
        List<Object> email = new ArrayList<>();

        for (int j = 0; j < size; j++) {
            //list.add(userList.get(j).getID());
            User user = userList.get(j);
            id.add(user.getID());
            name.add(user.getName());
            income.add(user.getIncome());
            savings.add(user.getSavings());
            email.add(user.getEmail());
        }
        value.add(id);
        value.add(name);
        value.add(income);
        value.add(savings);
        value.add(email);

        Sheets sheetService = SheetsServiceUtil.getSheetsService();

        String range = "Atlan Backend Challenge!A1";

        List<ValueRange> data = new ArrayList<>();
        data.add(new ValueRange().setMajorDimension("COLUMNS").setRange(range).setValues(value));

        BatchUpdateValuesRequest body = new BatchUpdateValuesRequest().
                setValueInputOption("RAW").setData(data);

        BatchUpdateValuesResponse result = sheetService.spreadsheets().values().
                batchUpdate(spreadSheetId, body).execute();
    }

    private void writeHeading() throws IOException, GeneralSecurityException {
        Sheets sheetService = SheetsServiceUtil.getSheetsService();
        ValueRange body = new ValueRange();
        body.setMajorDimension("ROWS");
        List<Object> head = new ArrayList<>();
        head.add("ID");
        head.add("Name");
        head.add("Income");
        head.add("Savings");
        head.add("Email");
        List<List<Object>> value = new ArrayList<>();
        value.add(head);
        body.setValues(value);

        String range = "Atlan Backend Challenge!A1";

        UpdateValuesResponse response = sheetService.spreadsheets().values().
                update(spreadSheetId, range, body).setValueInputOption("RAW").execute();
    }

    private void clearSheet() throws GeneralSecurityException, IOException {
        List<String> ranges = new ArrayList<>();
        ranges.add("Atlan Backend Challenge!A:A");
        ranges.add("Atlan Backend Challenge!B:B");
        ranges.add("Atlan Backend Challenge!C:C");
        ranges.add("Atlan Backend Challenge!D:D");
        ranges.add("Atlan Backend Challenge!E:E");

        BatchClearValuesRequest requestBody = new BatchClearValuesRequest();
        requestBody.setRanges(ranges);
        Sheets sheetService = sheetsServiceUtil.getSheetsService();
        Sheets.Spreadsheets.Values.BatchClear request =
                sheetService.spreadsheets().values().batchClear(spreadSheetId, requestBody);
        BatchClearValuesResponse response = request.execute();
    }
}
