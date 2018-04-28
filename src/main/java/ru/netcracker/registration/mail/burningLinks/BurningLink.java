package ru.netcracker.registration.mail.burningLinks;

import org.joda.time.DateTime;
import ru.netcracker.registration.model.DTO.UserDTO;

public class BurningLink {
    private String email;
    private DateTime whenSent;
    private static final String confirmationURI = "https://nc-edu-city-explorer-edu.193b.starter-ca-central-1.openshiftapps.com/confirm/";

    public BurningLink(String email){
        this.email=email;
        whenSent=DateTime.now();
    }

    public String getLink(){
        return confirmationURI+getCode();
    }

    public String getCode(){
        return hashCode()+"";
    }

    public String getEmail() {
        return email;
    }

    public boolean isValid(){
        DateTime now = DateTime.now();
        double nowMin = now.getYear()*365*24*60 + now.getDayOfYear()*24*60 + now.getMinuteOfDay();
        double whenSentMin = whenSent.getYear()*365*24*60 + whenSent.getDayOfYear()*24*60 + whenSent.getMinuteOfDay();
        double difference = nowMin-whenSentMin;
        return difference<=10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BurningLink that = (BurningLink) o;

        if (!email.equals(that.email)) return false;
        return whenSent.equals(that.whenSent);
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + whenSent.hashCode();
        return result;
    }
}
