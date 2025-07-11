package br.edu.ifpr.vhs.repositories;

import br.edu.ifpr.vhs.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
