package testoptional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.function.Supplier;

import org.junit.Test;

public class OptionalClassTest {

    class Language {
        String name;
        int users;     

        public Language(int u, String n) {
            name = n;
            users = u;
        }
    }

    @Test
    public void TestNull () {
        Optional<Language> maybeLang = Optional.ofNullable(null);
        assertFalse(maybeLang.isPresent());
    }   

    @Test
    public void TestEmpty () {
        Optional<Language> maybeLang = Optional.empty();        
        assertFalse(maybeLang.isPresent());
    }   


    @Test 
    public void TestIspresent () {
        Optional<Language> lang = Optional.of(new Language(300000, "Scala"));        
        assertTrue(lang.isPresent());
    }
    
    @Test
    public void TestGet () {
        Language lang = new Language(2500000, "Java");
        Optional<Language> maybeLang = Optional.of(lang);

        assertEquals("Java", maybeLang.get().name);
        assertEquals(2500000, maybeLang.get().users);
    }

    @Test
    public void TestIfPresent () {
        Optional<Language> maybeLang = Optional.of(new Language(2500000, "Java"));
        
        maybeLang.ifPresent(lang -> System.out.println("IfPresent: " + lang.name));
        //maybeLang.ifPresent(System.out::println);
    }

    @Test
    public void TestOrElse () {
        Language lang = new Language(300000, "Scala");
        
        Optional<Language> nullOptional = Optional.ofNullable(null);        
        Language orElseLanguage = nullOptional.orElse(lang);
        
        assertEquals("Scala", orElseLanguage.name);
    }   

    @Test
    public void TestSupplier () {
        
        Optional<Language> maybeLang = Optional.empty();
        Supplier<Language> defaulLanguage = () -> new Language(300000, "Scala");
        // lambda or implement anonymous class
        // new Supplier<Language>() {
        //    @Override
        //    public Framework get() { ... }

        Language lang = maybeLang.orElseGet(defaulLanguage);        
        assertEquals("Scala", lang.name);
    }   
    

    @Test(expected=IllegalStateException.class)
    public void TestOrElseThrow () {
        
        Optional<Language> maybeLang = Optional.empty();
        maybeLang.orElseThrow(IllegalStateException::new);        
    }   
    
}