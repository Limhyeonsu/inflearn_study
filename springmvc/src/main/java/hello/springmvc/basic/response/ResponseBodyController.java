package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@Controller
//@RestController  이 애너테이션을 사용하면 모든 메서드에 @ResponseBody가 적용되는 효과가 있다.
public class ResponseBodyController {

    //HTTP 메시지 바디에 직접 ok 응답 메시지를 전달
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    //ResponseEntity는 httpEntity를 상속받았는데 httpEntity는 HTTP 메시지의 헤더, 바디 정보를 가지고 있다.
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    //@ResponseBody를 사용하여 HTTP 메시지 컨버터를 통해 HTTP 메시지를 직접 입력할 수 있다.
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    //HTTP 메시지 컨버터를 통해 JSON 형식으로 변환되어 반환됨
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    //@ResponseBody는 응답코드를 설정하기 까다로운데 ResponseStatus를 통해 응답코드를 설정한다.
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}
