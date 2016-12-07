package testlambdas;

import lambdas.*;

import org.junit.*;

import static org.junit.Assert.assertEquals;

/* 
 Examples: Test Lambdas and methods references 
*/

public class MethodReferenceTest {
    @Test 
    public void callStaticMethod() {
        MethodReferenceExample methodRefs = new MethodReferenceExample();
        Integer r1 = methodRefs.operateInt((a, b) -> MethodReferenceExample.multiply(a, b), 2, 5);
        // Referencing static methods using Class Name     
        Integer r2 = methodRefs.operateInt(MethodReferenceExample::multiply, 5, 2); 
        assertEquals(r1, (Integer)10);
        assertEquals(r2, (Integer)10);
    }

    
    @Test 
    public void callNormalMethod() {
        MethodReferenceExample methodRefs = new MethodReferenceExample();        
        Integer r1 = methodRefs.operateInt((a, b) -> methodRefs.sum(a, b), 2, 8);        
        // Referencing Instance methods using Object Instance  
        Integer r2 = methodRefs.operateInt(methodRefs::sum, 2, 8);
        assertEquals(r1, (Integer)10);
        assertEquals(r2, (Integer)10);        
    }

    
    @Test 
    public void callMethodWithClassName() {
        MethodReferenceExample methodRefs = new MethodReferenceExample();  
        //Referencing Instance methods using Class Name   
        String r1 = methodRefs.operateString(s->s.toLowerCase(), "Test");    
        // now calling a non static method but using a class name. Then the instance of the class is passed when the method is called.    
        String r2 = methodRefs.operateString(String::toLowerCase, "TEST");
        assertEquals(r1, (String)"test");
        assertEquals(r2, (String)"test");  
        
    }   


}