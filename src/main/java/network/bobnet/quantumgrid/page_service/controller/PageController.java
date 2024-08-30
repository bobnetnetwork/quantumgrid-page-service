package network.bobnet.quantumgrid.page_service.controller;

import com.github.fge.jsonpatch.JsonPatch;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import network.bobnet.quantumgrid.commons.dto.response.SimplePage;
import network.bobnet.quantumgrid.commons.util.DtoUtil;
import network.bobnet.quantumgrid.page_service.dto.PageDto;
import network.bobnet.quantumgrid.page_service.dto.request.CreatePageRequest;
import network.bobnet.quantumgrid.page_service.entity.Page;
import network.bobnet.quantumgrid.page_service.service.PageService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pages")
public class PageController {

    private final PageService pageService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<SimplePage<PageDto>> getAllPages(
            @Parameter(hidden = true) @SortDefault(sort = "id") @PageableDefault(size = 20) final Pageable pageable
    ) {
        return ResponseEntity.ok(
                DtoUtil.convertSimplePage(
                        pageService.findAll(pageable),
                        PageDto.class
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageDto> getPageById(@PathVariable Long id) {
        return ResponseEntity.ok(modelMapper.map(pageService.findById(id), PageDto.class));
    }

    @PostMapping
    public ResponseEntity<Long> createPage(@RequestBody CreatePageRequest request) {
        Page pageEntity = modelMapper.map(request, Page.class);
        return ResponseEntity.ok(pageService.createPage(pageEntity).getId());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PageDto> patchPage(@PathVariable Long id, @RequestBody JsonPatch patch) {
        return ResponseEntity.ok(modelMapper.map(pageService.patch(id, patch), PageDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePage(@PathVariable Long id) {
        pageService.deletePage(id);
        return ResponseEntity.ok().build();
    }
}
