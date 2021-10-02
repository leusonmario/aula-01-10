package banco;

import banco.priv.TransferenciaTest;
import banco.user.ContaTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ContaTest.class, TransferenciaTest.class})
public class PrivateUserTestSuite {

}
