package emulator.controller;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SuppressWarnings("unused")
@Controller
public class AppErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

  @RequestMapping("/error")
  protected ResponseEntity<String> error(final RedirectAttributes redirectAttributes) throws IOException {
    redirectAttributes.addFlashAttribute("error", true);
    return new ResponseEntity<>("an error occurred", HttpStatus.BAD_REQUEST);
  }

  @Override public String getErrorPath() {
    return null;
  }
}
