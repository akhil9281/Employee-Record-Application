package com.akhil.controller;

import com.akhil.service.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Controller
public class SheetsContoller {

    @Autowired
    private SheetService sheetService;

    @RequestMapping(value = "/users/convertToSheets", method = RequestMethod.GET)
    public String convertToSheet() throws GeneralSecurityException, IOException {
        sheetService.writeInSheet();
        return "redirect:/toSheets";
    }

    @RequestMapping(value = "/toSheets")
    public RedirectView redirectToSheet() {
        RedirectView redirectView = new RedirectView();
        String url = "https://docs.google.com/spreadsheets/d/" +
                "1O_qmsoVYaQaav4BRCSzbvEE8-vXulZuS3Al7-Z_zyAQ/edit#gid=0";
        redirectView.setUrl(url);
        return redirectView;
    }
}
