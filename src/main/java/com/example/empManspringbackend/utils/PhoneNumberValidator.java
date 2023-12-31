//package com.example.empManspringbackend.utils;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.beans.factory.annotation.Value;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//@Configurable
//public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
//
//    @Value("${TWILIO_ACCOUNT_SID}")
//    private String twilioAccountSid;
//
//    @Value("${TWILIO_AUTH_TOKEN}")
//    private String twilioAuthToken;
//
//    @Override
//    public void initialize(ValidPhoneNumber constraintAnnotation) {
//        Twilio.init(twilioAccountSid, twilioAuthToken);
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//
//        // The Lookup API requires your phone number in E.164 format
//        // E.164 formatted phone numbers must not have spaces in them
//        value = value.replaceAll("[\\s()-]", "");
//
//        if ("".equals(value)){
//            return false;
//        }
//
//        try {
//            PhoneNumber.fetcher(new com.twilio.type.PhoneNumber(value)).fetch();
//            return true;
//
//        } catch (ApiException e){
//            // The Lookup API returns HTTP 404 if the phone number is not found
//            // (ie it is not a real phone number)
//            if (e.getStatusCode() == 404){
//                return false;
//            }
//            throw e;
//        }
//    }
//}
