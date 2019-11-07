package io.farkle.dignifiedfarkleservice.model.dao;

import io.farkle.dignifiedfarkleservice.model.entity.Passphrase;
import io.farkle.dignifiedfarkleservice.model.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface FarkleRepository {

  public interface PassphraseRepository extends CrudRepository<Passphrase, Long> {

    Optional<Passphrase> getPassphraseByUserAndKey(User user, String key);

    Iterable<Passphrase> getAllByUserOrderByKeyAsc(User user);

    Optional<Passphrase> getPassphraseByUserAndId(User user, long id);

  }
}
