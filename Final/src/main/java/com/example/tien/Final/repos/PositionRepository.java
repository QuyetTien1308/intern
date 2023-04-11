package com.example.tien.Final.repos;

import com.example.tien.Final.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PositionRepository extends JpaRepository<Position, Long> {

//    @Query(nativeQuery = true, value = "SELECT * FROM management.position_tbl u WHERE u.user_id=:userId ")
//    Position findByUser_Id(@Param("userId") Long userId);
    Position getPositionById(Long id);
    Position findAllByName(String name);
    Position findOneById(Long id);
}
