package network.bobnet.quantumgrid.page_service.entity;

import jakarta.persistence.*;
import lombok.*;
import network.bobnet.quantumgrid.page_service.enums.PageStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "pages", schema = "pageservice")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ToString.Include
    private Long id;

    @Column(name = "title", nullable = false)
    @ToString.Include
    private String title;

    @Column(name = "slug", nullable = false)
    @ToString.Include
    private String slug;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @ToString.Include
    private PageStatus status;

    @Column(name = "current_version_id")
    private Long currentVersionId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
