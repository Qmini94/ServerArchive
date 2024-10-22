package com.example.serverarchive.domain.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.experimental.SuperBuilder
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
abstract class BaseEntity(
    @CreatedDate
    @Column(updatable = false, name = "created_date")
    var createdDate: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_date")
    var updatedDate: LocalDateTime? = null
)