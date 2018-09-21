package com.movie.service;

import com.movie.model.Movie;

public interface OmdbMovieService {

	public Movie getOmdbMovieById(String imdbId);

	public Movie getOmdbMovieByTitle(String title);

}
