package io.farkle.dignifiedfarkleservice.service;

import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BundleProvider implements WordProvider {

  private final ResourceBundle bundle;

  @Autowired
  public BundleProvider(ResourceBundle bundle) {
    this.bundle = bundle;
  }

  @Override
  public Collection<String> words() {
    return bundle.keySet().parallelStream()
        .map(bundle::getString)
        .collect(Collectors.toList());
  }

}
