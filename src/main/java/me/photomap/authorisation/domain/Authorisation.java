package me.photomap.authorisation.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(name = "entity_user",columnList = "entityid,userid" )})
public class Authorisation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotEmpty
  @Column(name = "permission", columnDefinition="enum('write','read','read:write')")
  private String permission;

  @NotEmpty
  private String userid;

  @NotEmpty
  @Column(name="entityid",unique = true)
  private String entityid;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }


  public String getEntityid() {
    return entityid;
  }

  public void setEntityid(String entityid) {
    this.entityid = entityid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Authorisation that = (Authorisation) o;

    if (id != that.id) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return (int) (id ^ (id >>> 32));
  }
}
