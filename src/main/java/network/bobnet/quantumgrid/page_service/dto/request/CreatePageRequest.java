package network.bobnet.quantumgrid.page_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import network.bobnet.quantumgrid.page_service.enums.PageStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreatePageRequest {

    private String title;
    private String slug;
    private String content;
    private Long authorId;
    private PageStatus status;
}
