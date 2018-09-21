package com.movie.service;

import java.util.List;

import com.movie.model.Movie;

public interface MovieService {
	public List<Movie> getAllMovies();

	public Movie getMovieByImdbId(String imdbId);

	public Movie getMovieByTitle(String title);

	public List<Movie> getMovieByReleasedYear(String year);

	public List<Movie> getMovieByRating(int rating, String range);

	public List<Movie> getMovieByGenre(String genre);

	public void updateMovieRating(Movie movie);

	public void updateMovieGenres(Movie movie);
}
