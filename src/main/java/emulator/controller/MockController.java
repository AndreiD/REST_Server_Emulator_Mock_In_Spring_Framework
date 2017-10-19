package emulator.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MockController {

  public String cartJson = "";

  @RequestMapping(value = "/", method = RequestMethod.GET)
  protected ResponseEntity<String> publicIndex() {
    return new ResponseEntity<>("Server is running...", HttpStatus.OK);
  }

  //---------- LOGIN -----------

  @RequestMapping(value = "/api/v1/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  protected ResponseEntity<String> loginUser(@RequestBody String payload) throws IOException {

    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> map = mapper.readValue(payload, new TypeReference<Map<String, String>>() {
    });

    String email = map.get("email");
    String password = map.get("password");

    if (!email.equals("test@test.com") || !password.equals("123456")) {
      String message = "invalid username or password";
      String json = "{\"message\": \"" + message + "\"}";
      return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    String message = "ok";
    String json = "{\"message\": \"" + message + "\"}";
    return new ResponseEntity<>(json, HttpStatus.OK);
  }

  //---------- SIGNUP -----------

  @RequestMapping(value = "/api/v1/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  protected ResponseEntity<String> signUpUser(@RequestBody String payload) throws IOException {

    String message = "ok";
    String json = "{\"message\": \"" + message + "\"}";
    return new ResponseEntity<>(json, HttpStatus.OK);
  }

  //---------- PROMOTIONS -----------

  @RequestMapping(value = "/api/v1/promotions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  protected ResponseEntity<String> promotions() throws IOException {

    String promoUrl = "https://static.pexels.com/photos/2232/vegetables-italian-pizza-restaurant.jpg";
    String json = "{\"promo_url\": \"" + promoUrl + "\"}";
    return new ResponseEntity<>(json, HttpStatus.OK);
  }

  //--------- MENU -----------

  @RequestMapping(value = "/api/v1/menu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  protected ResponseEntity<String> getTheMenu() throws IOException, ParseException, URISyntaxException {
    JSONArray data = (JSONArray) new JSONParser().parse(
        new FileReader("C:\\workspace_idea\\emulator\\src\\main\\resources\\static\\menu.json"));
    return new ResponseEntity<>(data.toJSONString(), HttpStatus.OK);
  }

  //--------- CART -----------

  @RequestMapping(value = "/api/v1/cart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  protected ResponseEntity<String> getTheCart() throws IOException, ParseException, URISyntaxException {
    return new ResponseEntity<>(cartJson, HttpStatus.OK);
  }

  @RequestMapping(value = "/api/v1/cart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  protected ResponseEntity<String> postTheCart(@RequestBody String payload) throws IOException, ParseException, URISyntaxException {

    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> map = mapper.readValue(payload, new TypeReference<Map<String, Object>>() {
    });

    ArrayList<Object> products = (ArrayList<Object>) map.get("products");
    double total_price = 0;
    for (int i = 0; i < products.size(); i++) {
      Map<String, Object> productMap = (Map<String, Object>) products.get(i);
      total_price = total_price + Math.round(Double.valueOf(String.valueOf(productMap.get("price"))));
    }

    DecimalFormat df = new DecimalFormat("#.##");
    map.put("total", df.format(total_price));
    map.put("vat", df.format(total_price * 0.20));

    return new ResponseEntity<>(new ObjectMapper().writeValueAsString(map), HttpStatus.OK);
  }

  //------- PROMO CODES ----------

  @RequestMapping(value = "/api/v1/promo_codes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  protected ResponseEntity<String> getPromoCodes() throws IOException, ParseException, URISyntaxException {
    JSONArray data = (JSONArray) new JSONParser().parse(
        new FileReader("C:\\workspace_idea\\emulator\\src\\main\\resources\\static\\promo_codes.json"));
    return new ResponseEntity<>(data.toJSONString(), HttpStatus.OK);
  }

  //------ IMAGES --------
  @RequestMapping(value = "/i/{imageName:.+}")
  @ResponseBody
  public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
    File serverFile = new File("C:\\workspace_idea\\emulator\\src\\main\\resources\\static\\images\\" + imageName);
    return Files.readAllBytes(serverFile.toPath());
  }
}
