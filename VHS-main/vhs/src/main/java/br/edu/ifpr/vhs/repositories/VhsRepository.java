package br.edu.ifpr.vhs.repositories;

import br.edu.ifpr.vhs.entities.Vhs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VhsRepository extends JpaRepository<Vhs, Long> {}
