package com.akhil.service;

import com.akhil.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    public boolean incomeValidation(User user) {
        return user.getIncome() >= user.getSavings();
    }

    public boolean emailValidation(User user) {
        if (true){
            return true;
        }
        return false;
    }
}
