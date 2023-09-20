package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * The ApplicationEntity class serves as a base entity for all entities in the application,
 * providing common fields and functionality such as unique identifiers and timestamps.
 *
 * @param <T> The type of payload that can be used to update this entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public abstract class ApplicationEntity<T> {

    /**
     * The unique identifier for each entity in the application.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    protected UUID id;

    /**
     * The timestamp indicating when this entity was created.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date createdAt;

    /**
     * The timestamp indicating when this entity was last updated.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date updatedAt;

    /**
     * An abstract method for updating the entity's data with a given payload.
     *
     * @param payload The payload containing updated data.
     */
    public abstract void update(T payload);
}
