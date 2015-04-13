package me.photomap.authorisation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by craigbrookes on 06/04/15.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorisedException extends RuntimeException {
  public UnauthorisedException() {
    super("no access to resource");
  }
}
