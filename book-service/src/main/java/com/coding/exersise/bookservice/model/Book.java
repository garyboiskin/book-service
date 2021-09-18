package com.coding.exersise.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Accessors
@Data
public class Book {
    @Id
    @NotNull
    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private  String isbn;
    @NotNull
    @NotBlank(message = "Title is required")
    private String title;
    @NotNull
    @NotBlank(message = "Author is required")
    @Pattern(regexp="[^0-9]*")
    private String author;
    @NotNull
    @Past
    private  LocalDate datePublished;
    private Integer rating;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getTitle().equals(book.getTitle()) && getAuthor().equals(book.getAuthor()) && getDatePublished().equals(book.getDatePublished());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getDatePublished());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Book{");
        sb.append("isbn='").append(isbn).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", datePublished=").append(datePublished);
        sb.append(", rating=").append(rating);
        sb.append('}');
        return sb.toString();
    }
}
