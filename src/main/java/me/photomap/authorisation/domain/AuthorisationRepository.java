package me.photomap.authorisation.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by craigbrookes on 21/03/15.
 */
public interface AuthorisationRepository extends CrudRepository<Authorisation,Long> {

  List<Authorisation> findByUserid(String userId);
  Authorisation findByUseridAndEntityid(String userId, String entityId);
}
