package com.zerobase.zerobaseprojectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql의 autoincreasement는 IDENTITY
    private Long id;

    @Setter @ManyToOne(optional = false) private UserAccount userAccount; // 유저 정보 (ID)

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Column(nullable = false, length = 10000) private String content; // 내용

    // nullable은 true가 default값
    @Setter
    private String hashtag; // 해시태그

    // One to Many - Coment들을 중복을 허용하지 않고 모아서 보여줌
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();


    protected Article() {
    }

    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        return new Article(userAccount, title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
        // id를 아직 부여받지 않았을 때 - entity만 있고 영속화를 하지 않았을 때(db에 insert되기 전)
        // 막 만든 아직 영속화 되지 않은 entity는 동등성 검사에서 탈락됨.(다른값으로 취급)
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
