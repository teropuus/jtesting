package lambdas;

public class Person implements Comparable<Person> {
  private String surname;
  private String firstname;

  public Person (String f, String s){
    firstname = f;
    surname = s;
  }

  public String getFirstname(){
    return firstname;
  }

  public String getSurname(){
    return surname;
  }

  public void setFirstname(String name){
    firstname = name;
  }

  public void setSurname(String name){
    surname = name;
  }

  public String toString() {
   return surname + ", " + firstname; 
  }

  public int compareTo(Person other) {
    if (surname.equals(other.surname)) {
      return firstname.compareTo(other.firstname);
    }
    return surname.compareTo(other.surname);
  }

}

