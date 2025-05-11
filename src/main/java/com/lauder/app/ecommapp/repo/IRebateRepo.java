package com.lauder.app.ecommapp.repo;


import com.lauder.app.ecommapp.model.RebateEarned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRebateRepo extends JpaRepository<RebateEarned, Long> {
}
