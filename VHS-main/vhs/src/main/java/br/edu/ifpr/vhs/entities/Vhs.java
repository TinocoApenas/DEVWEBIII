package br.edu.ifpr.vhs.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Vhs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String title;

    @NotBlank(message = "O diretor é obrigatório")
    private String director;

    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "A categoria é obrigatória")
    private Category category;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status é obrigatório")
    private VhsStatus status;
}