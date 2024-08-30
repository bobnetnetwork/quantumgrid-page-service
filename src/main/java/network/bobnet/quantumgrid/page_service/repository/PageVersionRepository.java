package network.bobnet.quantumgrid.page_service.repository;

import network.bobnet.quantumgrid.page_service.entity.PageVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageVersionRepository extends JpaRepository<PageVersion, Long> {

    List<PageVersion> findByPageIdOrderByVersionNumberDesc(Long pageId);

    void deleteByPageId(Long pageId);
}
