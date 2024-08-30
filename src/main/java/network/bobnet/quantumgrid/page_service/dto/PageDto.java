package network.bobnet.quantumgrid.page_service.dto;

import lombok.*;
import network.bobnet.quantumgrid.page_service.enums.PageStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link network.bobnet.quantumgrid.page_service.entity.Page}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1869070511733659435L;

    private Long id;
    private String title;
    private String slug;
    private String content;
    private Long authorId;
    private PageStatus status;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
