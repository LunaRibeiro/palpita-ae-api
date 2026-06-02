package br.com.luna.palpita.ae.api.core.predictiongroup.repository;

import br.com.luna.palpita.ae.api.core.predictiongroup.domain.entity.PredictionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionGroupRepository extends JpaRepository<PredictionGroup, Long>, JpaSpecificationExecutor<PredictionGroup> {
}
