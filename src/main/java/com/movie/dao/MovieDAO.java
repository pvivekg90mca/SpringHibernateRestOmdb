package com.movie.dao;

import java.util.List;

import com.movie.model.Movie;

public interface MovieDAO {
	public List<Movie> getAllMovies();

	public List<Movie> getMovieByImdbId(String imdbId);

	public List<Movie> getMovieByTitle(String title);

	public List<Movie> getMovieByYearRange(int startYear, int endYear);

	public List<Movie> getMovieByYear(int year);

	public List<Movie> getMovieByHigherRating(int rating);

	public List<Movie> getMovieByLowerRating(int rating);

	public List<Movie> getMovieByGenre(String genre);

	public void updateMovieRating(Movie movie);

	public void updateMovieGenres(Movie movie);

	public void addOmdbMovie(Movie movie);

}
