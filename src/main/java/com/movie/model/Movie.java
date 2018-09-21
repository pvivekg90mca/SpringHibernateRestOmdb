package com.movie.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "MOVIE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Movie")
public class Movie implements Serializable {
	private static final long serialVersionUID = -1232395859408322328L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "RELEASED_YEAR")
	private int releasedYear;

	@Column(name = "RATING")
	private int rating;

	@Column(name = "GENRES")
	private String genres;

	@Column(name = "IMDB_ID")
	private String imdbId;

	public Movie() {

	}

	public Movie(int id, String title, int releasedYear, int rating, String genres, String imdbId) {
		super();
		this.id = id;
		this.title = title;
		this.releasedYear = releasedYear;
		this.rating = rating;
		this.genres = genres;
		this.imdbId = imdbId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", releasedYear=" + releasedYear + ", rating=" + rating
				+ ", genres=" + genres + ", imdbId=" + imdbId + "]";
	}

}
