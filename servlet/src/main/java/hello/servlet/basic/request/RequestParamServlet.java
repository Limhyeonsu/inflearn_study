package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //전체 파라미터 조회
        Enumeration<String> parameterNames = request.getParameterNames();

        //파라미터 명에 따른 값 꺼내기
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        //?username=hello&age=20&username=hello2  ==> username을 중복으로 넘길수도 있다
        //이름이 같은 복수 파라미터 조회
        String[] usernames = request.getParameterValues("username");

    }
}
