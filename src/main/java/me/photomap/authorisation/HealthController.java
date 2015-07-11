package me.photomap.authorisation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

  @RequestMapping(value = "/health", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String,String> health(){
    HashMap<String,String> healthMap = new HashMap<>();
    healthMap.put("http","ok");
    return healthMap;
  }

}
