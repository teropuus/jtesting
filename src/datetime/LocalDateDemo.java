package datetime;

import java.time.*;

/**
 * Example
 * using java.time.LocalDate
 *
 */
public class LocalDateDemo {

    public static void main(String[] args) throws Exception {
       LocalDate today = LocalDate.now();
       LocalDate birthday = LocalDate.of(1969, 5, 5);
       Period fromBirth = Period.between(birthday, today);
       LocalDate calculatedBirthday = today.minus(fromBirth);      
       System.out.println("today: " + today);      
       System.out.println("date of birth: " + birthday);      
       System.out.println("calculated birhtday : " + calculatedBirthday);      

       System.out.println("Age now: " + fromBirth.getYears() + " years " + fromBirth.getMonths() + " months " + fromBirth.getDays() + " days.");      


      LocalDate date = LocalDate.now(); // any date
      LocalDateTime timestamp = LocalDateTime.of(date, LocalTime.MIDNIGHT);
      System.out.println("Looping time from midnight until this day");
      do {
        System.out.println(timestamp.toLocalTime());
        timestamp = timestamp.plus(Duration.ofHours(1).plusMinutes(13));
      } while (date.equals(timestamp.toLocalDate()));
      System.out.println(timestamp.toLocalTime());
    }

}