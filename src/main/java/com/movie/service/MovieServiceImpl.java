package com.movie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dao.MovieDAOImpl;
import com.movie.model.Movie;

@Service
public class MovieServiceImpl implements MovieService {

	public static final String RATING_HIGHER = "h";
	public static final String RATING_LOWER = "l";

	@Autowired
	private OmdbMovieServiceImpl omdbMovieServiceImpl;

	@Autowired
	private MovieDAOImpl movieDAOImpl;

	@Override
	public List<Movie> getAllMovies() {
		return movieDAOImpl.getAllMovies();
	}

	@Override
	public Movie getMovieByImdbId(String imdbId) {
		List<Movie> movieList = movieDAOImpl.getMovieByImdbId(imdbId);

		if (!movieList.isEmpty()) { // Movie found in local DB
			return movieList.get(0);
		} else {
			// Movie not found in local DB, calling OMDB API 
			Movie omdbMovie = omdbMovieServiceImpl.getOmdbMovieById(imdbId);
			if (null != omdbMovie) {
				// Insert new movie in local DB for future reference
				movieDAOImpl.addOmdbMovie(omdbMovie);
			}
			return omdbMovie;
		}
	}

	@Override
	public Movie getMovieByTitle(String title) {
		List<Movie> movieList = movieDAOImpl.getMovieByTitle(title);

		if (!movieList.isEmpty()) { // Movie found in local DB
			return movieList.get(0);
		} else {
			// Movie not found in local DB, calling OMDB API
			Movie omdbMovie = omdbMovieServiceImpl.getOmdbMovieByTitle(title);
			if (null != omdbMovie) {
				// Insert new movie in local DB for future reference
				movieDAOImpl.addOmdbMovie(omdbMovie);
			}
			return omdbMovie;
		}
	}

	@Override
	public List<Movie> getMovieByReleasedYear(String year) {
		String[] years;
		List<Movie> movieList;

		if (year.indexOf("-") > -1) { // Released years range
			years = year.split("-");
			movieList = movieDAOImpl.getMovieByYearRange(Integer.parseInt(years[0]), Integer.parseInt(years[1]));
		} else { // Single released year
			movieList = movieDAOImpl.getMovieByYear(Integer.parseInt(year));
		}

		return movieList;
	}

	@Override
	public List<Movie> getMovieByRating(int rating, String range) {

		List<Movie> movieList;
		if (RATING_HIGHER.equals(range)) { // >= rating
			movieList = movieDAOImpl.getMovieByHigherRating(rating);
		} else if (RATING_LOWER.equals(range)) { // <= rating
			movieList = movieDAOImpl.getMovieByLowerRating(rating);
		} else {
			movieList = new ArrayList<Movie>();
		}
		return movieList;
	}

	@Override
	public List<Movie> getMovieByGenre(String genre) {
		return movieDAOImpl.getMovieByGenre(genre);
	}

	@Override
	public void updateMovieRating(Movie movie) {

		movieDAOImpl.updateMovieRating(movie);
	}

	@Override
	public void updateMovieGenres(Movie movie) {
		movieDAOImpl.updateMovieGenres(movie);
	}

}