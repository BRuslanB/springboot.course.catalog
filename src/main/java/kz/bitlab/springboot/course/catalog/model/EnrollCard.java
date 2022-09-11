package kz.bitlab.springboot.course.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_enroll_cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

    @Column(name = "rating")
    private int userRating; // 1,2,3,4,5 {User Rating}

    @Column(name="comment")
    private String comment;
}