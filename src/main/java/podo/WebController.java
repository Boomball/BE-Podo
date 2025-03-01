package podo;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController implements ErrorController {

    // 새로고침 시 dashboard로 매핑되는 컨트롤러가 존재하지 않아 발생하는 404 found를 해결
    @GetMapping({
            "/dashboard"
    })
    public String redirect() {
        // index.html은 리액트 프로젝트를 빌드 후 /resources/static에 위치시킨다
        return "index.html";
    }
}
