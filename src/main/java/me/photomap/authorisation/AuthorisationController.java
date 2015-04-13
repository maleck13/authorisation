package me.photomap.authorisation;

import me.photomap.authorisation.domain.Authorisation;
import me.photomap.authorisation.domain.AuthorisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthorisationController {

  private @Autowired AuthorisationRepository authorisationRepository;


  @RequestMapping(value = "/authorisation/{userid}/{entity}/{access}", method = RequestMethod.GET)
  public Authorisation get(@PathVariable("userid") String userid, @PathVariable("entity") String entity, @PathVariable("access") String access){
    Authorisation auth = authorisationRepository.findByUseridAndEntityid(userid, entity);
    if(null == auth){
      throw new NotFoundException();
    }
    if(auth.getPermission().equals(access))
      return auth;
    else{
      throw new UnauthorisedException();
    }
  }

  @RequestMapping(value = "/authorisation", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_VALUE)
  public Authorisation post(@Valid @RequestBody Authorisation auth){
    return authorisationRepository.save(auth);
  }

  @RequestMapping(value = "/authorisation/{authid}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public Authorisation put(@RequestBody Authorisation auth, @PathVariable("authid")Long id){
    Authorisation dbAuth = authorisationRepository.findOne(id);
    if(null == dbAuth){
      throw new NotFoundException();
    }
    if(null != auth.getPermission()){
      dbAuth.setPermission(auth.getPermission());
    }
    if(null != auth.getEntityid()){
      dbAuth.setEntityid(auth.getEntityid());
    }
    return authorisationRepository.save(dbAuth);
  }

}
