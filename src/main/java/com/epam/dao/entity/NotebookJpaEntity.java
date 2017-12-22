package com.epam.dao.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = {"id", "user"})
@Table(name = "notebooks", schema = "public")
public class NotebookJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id",
      nullable = false)
  private long id;

  @NonNull
  @Column(name = "name",
      nullable = false,
      length = -1)
  private String name;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id",
      referencedColumnName = "id",
      nullable = false)
  private UserJpaEntity user;

  @OneToMany(mappedBy = "notebook", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<NoteJpaEntity> notes;
}
