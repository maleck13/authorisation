package me.photomap.authorisation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by craigbrookes on 18/04/15.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{
}
