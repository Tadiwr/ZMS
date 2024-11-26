package com.tadiwa.backend.features.member;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tadiwa.backend.features.cell.Cell;


@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    public Long countByIdNumber(String idNumber);

    public Optional<Member> findByCellAndId(Cell cell, Long id);
}
