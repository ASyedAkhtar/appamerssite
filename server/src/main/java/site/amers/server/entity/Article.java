package site.amers.server.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
// import jakarta.persistence.PrePersist;
// import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(schema = "amerssite", name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_generator")
    @SequenceGenerator(name = "article_generator", sequenceName = "sq_article", allocationSize = 1)
    @Column(nullable = false, name = "id_article")
    private Long id;

    @Column(nullable = false, length = 1024)
    private String title;

    @Lob
    @Column(columnDefinition = "text")
    private String content;

    @CreationTimestamp
    @Column(nullable = false, name = "dt_created")
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(nullable = false, name = "dt_modified")
    private LocalDateTime dateModified;

    // @PrePersist
    // protected void onCreate() {
    //     this.dateCreated = LocalDateTime.now();
    // }

    // @PreUpdate
    // protected void onUpdate() {
    //     this.dateModified = LocalDateTime.now();
    // }

    public Article() {}
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "[Article " + this.id + ": " + this.title + "]";
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    // public void setDateCreated(LocalDateTime dateCreated) {
    //     this.dateCreated = dateCreated;
    // }
    public LocalDateTime getDateModified() {
        return dateModified;
    }
    // public void setDateModified(LocalDateTime dateModified) {
    //     this.dateModified = dateModified;
    // }
}
