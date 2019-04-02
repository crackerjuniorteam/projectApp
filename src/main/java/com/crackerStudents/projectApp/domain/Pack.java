package com.crackerStudents.projectApp.domain;

        import lombok.Data;
        import java.sql.Date;
        import java.util.ArrayList;
        import java.util.List;
        import javax.persistence.*;


@Entity
@Table(name = "pack", schema = "memory")
@Data
public class Pack {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "author_id")
    private int authorId;

    @Basic
    @Column(name = "is_public")
    private boolean isPublic;

    @Basic
    @Column(name = "likes")
    private int likes;

    @Basic
    @Column(name = "created")
    private Date created;

    @ManyToMany
    @JoinTable(name = "user_packs",
            joinColumns = {@JoinColumn(name = "pack_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tag_to_pack",
            joinColumns = {@JoinColumn(name = "pack_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags = new ArrayList<>();


}