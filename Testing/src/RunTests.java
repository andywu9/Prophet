
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


//this is the main java file that will run the other java files
@RunWith(Suite.class)
@SuiteClasses({HomePage.class,AboutPage.class,SignUpPage.class,SignInPage.class})
public class RunTests {
	
}