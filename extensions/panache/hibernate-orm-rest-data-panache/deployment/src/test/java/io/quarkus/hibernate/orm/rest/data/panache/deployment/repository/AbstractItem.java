package io.quarkus.hibernate.orm.rest.data.panache.deployment.repository;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@MappedSuperclass
public abstract class AbstractItem<IdType extends Number> extends AbstractEntity<IdType> {

    public String name;

    @ManyToOne(optional = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    public Collection collection;
}
