package med.voll.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.modelo.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    
}