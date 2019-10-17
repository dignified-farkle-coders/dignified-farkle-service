package io.farkle.dignifiedfarkleservice.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"userName","playerId"}),
    indexes = @Index(columnList = "userName")
)
public class Player {

}
