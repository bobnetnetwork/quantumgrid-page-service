package network.bobnet.quantumgrid.page_service.service;

import com.github.fge.jsonpatch.JsonPatch;
import network.bobnet.quantumgrid.commons.dto.response.SimplePage;
import network.bobnet.quantumgrid.commons.exceptions.NotFoundException;
import network.bobnet.quantumgrid.commons.service.AbstractService;
import network.bobnet.quantumgrid.page_service.entity.Page;
import network.bobnet.quantumgrid.page_service.entity.PageVersion;
import network.bobnet.quantumgrid.page_service.repository.PageRepository;
import network.bobnet.quantumgrid.page_service.repository.PageVersionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PageService extends AbstractService<Page> {

    private final PageRepository pageRepository;
    private final PageVersionRepository pageVersionRepository;

    public PageService(PageRepository pageRepository, PageVersionRepository pageVersionRepository) {
        super(Page.class, pageRepository);
        this.pageRepository = pageRepository;
        this.pageVersionRepository = pageVersionRepository;
    }

    public SimplePage<Page> findAll(final Pageable pageable) {
        return getPageableEntityList(pageable);
    }

    public Page findById(final Long id) {
        return findEntityById(id);
    }

    public Page createPage(Page page) {
        Page savedPage = createEntityToRepository(page);
        saveNewVersion(savedPage);
        return savedPage;
    }

    public void deletePage(Long id) {
        pageVersionRepository.deleteByPageId(id);

        deleteEntityById(id);
    }

    public Page patch(Long id, JsonPatch patch) {
        Page page = patchEntity(id, patch);
        saveNewVersion(page);
        return page;
    }

    public List<PageVersion> getPageVersions(Long pageId) {
        return pageVersionRepository.findByPageIdOrderByVersionNumberDesc(pageId);
    }

    public Page revertToVersion(Long pageId, Integer versionNumber) {
        Optional<PageVersion> versionOptional = pageVersionRepository.findByPageIdOrderByVersionNumberDesc(pageId)
                .stream()
                .filter(v -> v.getVersionNumber().equals(versionNumber))
                .findFirst();

        if (versionOptional.isEmpty()) {
            throw new NotFoundException("Version not found");
        }

        PageVersion pageVersion = versionOptional.get();
        Optional<Page> pageOptional = pageRepository.findById(pageId);
        if (pageOptional.isEmpty()) {
            throw new NotFoundException("Page not found");
        }

        Page page = pageOptional.get();
        page.setContent(pageVersion.getContent());
        page.setUpdatedAt(LocalDateTime.now());
        page.setUpdatedBy(pageVersion.getCreatedBy());
        pageRepository.save(page);

        saveNewVersion(page);
        return page;
    }

    private void saveNewVersion(Page page) {
        int nextVersionNumber = pageVersionRepository.findByPageIdOrderByVersionNumberDesc(page.getId())
                .stream()
                .map(PageVersion::getVersionNumber)
                .findFirst()
                .orElse(0) + 1;

        PageVersion version = PageVersion.builder()
                .pageId(page.getId())
                .versionNumber(nextVersionNumber)
                .content(page.getContent())
                .createdAt(LocalDateTime.now())
                .createdBy(page.getUpdatedBy() != null ? page.getUpdatedBy() : page.getCreatedBy())
                .build();

        pageVersionRepository.save(version);
        page.setCurrentVersionId(version.getId());
        pageRepository.save(page);
    }
}
