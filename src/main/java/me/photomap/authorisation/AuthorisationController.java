package me.photomap.authorisation;

import me.photomap.authorisation.domain.Authorisation;
import me.photomap.authorisation.domain.AuthorisationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthorisationController {

  private @Autowired AuthorisationRepository authorisationRepository;


  private String getEntityId(Authorisation auth){
    return auth.getUserid() + ":" + auth.getEntityid();
  }

  private String getEntityId(String userId, String entityId){
    return userId + ":" + entityId;
  }

  Logger log = LoggerFactory.getLogger(AuthorisationController.class);

  @RequestMapping(value = "/authorisation/{userid}/{entity}/{access}", method = RequestMethod.GET)
  public Authorisation has(@PathVariable("userid") String userid, @PathVariable("entity") String entity, @PathVariable("access") String access){
    String entityId = getEntityId(userid,entity);
    log.info("entityid " + entityId + " access " + access);
    Authorisation auth = authorisationRepository.findByEntityid(entityId);
    if(null == auth){
      log.info("no auth foound");
      throw new UnauthorisedException();
    }

    if(auth.getPermission().equals(access) || "read:write".equals(auth.getPermission()))
      return auth;
    else{
      throw new UnauthorisedException();
    }
  }



  @RequestMapping(value = "/authorisation", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_VALUE)
  public Authorisation post(@Valid @RequestBody Authorisation auth){
    String computedEntityId = getEntityId(auth);
    auth.setEntityid(computedEntityId);
    Authorisation exists = authorisationRepository.findByEntityid(computedEntityId);
    if (null != exists){
      throw new ConflictException();
    }
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
