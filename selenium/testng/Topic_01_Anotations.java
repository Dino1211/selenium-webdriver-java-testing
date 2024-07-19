package tesng;


import org.testng.annotations.*;

public class Topic_01_Anotations {


    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");

    }
    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");

    }
    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");

    }
    @AfterTest
    public void afterTest() {
        System.out.println("After Test");
    }



    @Test
    public void TC_01_Basic_Form() {
        System.out.println("Basic Form");

    }

    @Test
    public void TC_02_Tech_Panda() {
    }



}


