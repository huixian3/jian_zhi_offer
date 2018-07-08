package chapter_dp.javaee.intercepting;

/**
 * 创建实体过滤器。
 * @author yuanhao
 * @date 2018/7/7 20:56
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void execute(String request) {
        System.out.println("Authenticating request: " + request);
    }

}
