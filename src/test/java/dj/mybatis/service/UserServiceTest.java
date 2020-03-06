package dj.mybatis.service;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest(UserService.class)
@RunWith(PowerMockRunner.class)
public class UserServiceTest {
    @Mock
    /*默认把这个类交给mock依赖注入*/
    private UserService userService;

    @Before
    public void init() throws Exception {
    }

    @Test
    public void main() {
    }

    @Test
    public void getData() {
        PowerMockito.mockStatic(UserService.class);
        when(UserService.getData(12)).thenReturn(156);
        //测试提交2

        TestCase.assertEquals(156, UserService.getData(12));
    }

    @Test
    public void helloMethod() throws Exception {
        /*静态方法时候需要反射类*/
        PowerMockito.mockStatic(UserService.class);
        when(UserService.helloMethod()).thenReturn("I am a static mock method.");
        TestCase.assertEquals("I am a static mock method.", UserService.helloMethod());
    }

    @Test
    public void finalMethod() {
        when(userService.finalMethod()).thenReturn("I am a static mock method.");
        TestCase.assertEquals("I am a static mock method.", userService.finalMethod());
    }
}