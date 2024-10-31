package org.youcode.itlens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.itlens.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
