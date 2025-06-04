package com.softwaretesting.testing.validator;

import org.springframework.stereotype.Service;
import java.util.regex.*;

@Service
public class PhoneNumberValidator {

    public boolean validate(String p_number){
        Pattern p = Pattern.compile("^\\+49\\d{11}$");
        Matcher m = p.matcher(p_number);
        return (m.matches());
    }
}
