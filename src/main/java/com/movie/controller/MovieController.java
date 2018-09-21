package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movie.model.Movie;
import com.movie.service.MovieServiceImpl;

@RestController
public class MovieController {
	@Autowired
	private MovieServiceImpl movieServiceImpl;

	/**
	 * Retrieve all the Movies
	 * 
	 * @return List<Movie>
	 */
	@RequestMapping(value = "/movies", produces = "application/json", method = RequestMethod.GET)
	public List<Movie> getAllMovies() {
		List<Movie> movieList = movieServiceImpl.getAllMovies();
		return movieList;
	}

	/**
	 * Retrieve a movie with imdbId
	 * 
	 * @param imdbId
	 * @return Movie
	 */
	@RequestMapping(value = "/movie/{imdbId}", produces = "application/json", method = RequestMethod.GET)
	public Movie getMovieByImdbId(@PathVariable("imdbId") String imdbId) {
		Movie movie = movieServiceImpl.getMovieByImdbId(imdbId);
		return movie;
	}

	/**
	 * Retrieve a movie with Title
	 * 
	 * @param title
	 * @return Movie
	 */
	@RequestMapping(value = "/movie/title/{title}", produces = "application/json", method = RequestMethod.GET)
	public Movie getMovieByTitle(@PathVariable("title") String title) {
		Movie movie = movieServiceImpl.getMovieByTitle(title);
		return movie;
	}

	/**
	 * Retrieve the Movie(s) with released year/years range
	 * 
	 * @param releasedYear
	 * @return List<Movie>
	 */
	@RequestMapping(value = "/movie/year/{year}", produces = "application/json", method = RequestMethod.GET)
	public List<Movie> getMovieByReleasedYear(@PathVariable("year") String year) {
		List<Movie> movieList = movieServiceImpl.getMovieByReleasedYear(year);
		return movieList;
	}

	/**
	 * Retrieve the Movie(s) by rating
	 * 
	 * rating: 1-5 range: {h=higher, l=lower}
	 * 
	 * @param rating
	 * @param range
	 * @return
	 */
	@RequestMapping(value = "/movie/ratings/{rating}/{range}", produces = "application/json", method = RequestMethod.GET)
	public List<Movie> getMovieByRating(@PathVariable("rating") int rating, @PathVariable("range") String range) {
		List<Movie> movieList = movieServiceImpl.getMovieByRating(rating, range);
		return movieList;
	}

	/**
	 * Retrieve the Movie(s) by Genres
	 * 
	 * @param genre
	 * @return
	 */
	@RequestMapping(value = "/movie/genres/{genre}", produces = "application/json", method = RequestMethod.GET)
	public List<Movie> getMovieByRating(@PathVariable("genre") String genre) {
		List<Movie> movieList = movieServiceImpl.getMovieByGenre(genre);
		return movieList;
	}

	/**
	 * Update movie rating
	 * 
	 * @param movie
	 */
	@RequestMapping(value = "/movies/update/rating", method = RequestMethod.PUT, 
			produces = "application/json", consumes = "application/json")
	public void updateMovieRating(@RequestBody Movie movie) {
		movieServiceImpl.updateMovieRating(movie);
	}

	/**
	 * Update movie genres
	 * 
	 * @param movie
	 */
	@RequestMapping(value = "/movies/update/genres", method = RequestMethod.PUT, 
			produces = "application/json", consumes = "application/json")
	public void updateMovieGenres(@RequestBody Movie movie) {
		movieServiceImpl.updateMovieGenres(movie);
	}

}
