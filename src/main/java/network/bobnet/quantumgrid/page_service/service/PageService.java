package network.bobnet.quantumgrid.page_service.service;

import com.github.fge.jsonpatch.JsonPatch;
import network.bobnet.quantumgrid.commons.dto.response.SimplePage;
import network.bobnet.quantumgrid.commons.service.AbstractService;
import network.bobnet.quantumgrid.page_service.entity.Page;
import network.bobnet.quantumgrid.page_service.repository.PageRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PageService extends AbstractService<Page> {

    public PageService(PageRepository pageRepository) {
        super(Page.class, pageRepository);
    }

    public SimplePage<Page> findAll(final Pageable pageable) {
        return getPageableEntityList(pageable);
    }

    public Page findById(final Long id) {
        return findEntityById(id);
    }

    public Page createPage(Page page) {
        return createEntityToRepository(page);
    }

    public void deletePage(Long id) {
        deleteEntityById(id);
    }

    public Page patch(Long id, JsonPatch patch) {
        return patchEntity(id, patch);
    }
}
