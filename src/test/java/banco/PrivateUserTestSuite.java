package banco;

import banco.priv.TransferenciaTest;
import banco.user.ContaTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ContaTest.class, TransferenciaTest.class})
public class PrivateUserTestSuite {

}
