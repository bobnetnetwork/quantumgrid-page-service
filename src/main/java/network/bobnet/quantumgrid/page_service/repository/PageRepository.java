package network.bobnet.quantumgrid.page_service.repository;

import network.bobnet.quantumgrid.page_service.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
}
