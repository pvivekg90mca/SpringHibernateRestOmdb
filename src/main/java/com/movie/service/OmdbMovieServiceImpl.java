package com.movie.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.movie.model.Movie;

@Service
public class OmdbMovieServiceImpl implements OmdbMovieService {

	private static final int SUCCESS = 200;
	private static final int RATING = 4;
	private static final String NA = "N/A";
	Movie movie;
	String response = "";

	@Override
	public Movie getOmdbMovieById(String imdbId) {

		try {
			URL url = new URL("http://www.omdbapi.com/?i=" + imdbId + "&apikey=de92e0da");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responseCode = conn.getResponseCode();

			if (SUCCESS == responseCode) {
				JSONObject omdbMovie = processResponse(url);

				if (!omdbMovie.isEmpty()) {
					movie = createMovie(omdbMovie);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return movie;
	}

	@Override
	public Movie getOmdbMovieByTitle(String title) {
		try {
			URL url = new URL("http://www.omdbapi.com/?t=" + title + "&apikey=de92e0da");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responseCode = conn.getResponseCode();

			if (SUCCESS == responseCode) {

				JSONObject omdbMovie = processResponse(url);

				if (!omdbMovie.isEmpty()) {
					movie = createMovie(omdbMovie);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return movie;
	}

	private JSONObject processResponse(URL url) {

		JSONObject omdbMovie = new JSONObject();
		try {
			Scanner sc;
			sc = new Scanner(url.openStream());
			if (sc.hasNext()) {
				response = sc.nextLine();
			}
			sc.close();
			JSONParser parser = new JSONParser();
			omdbMovie = (JSONObject) parser.parse(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return omdbMovie;
	}

	private Movie createMovie(JSONObject omdbMovie) {

		Movie movie = new Movie();

		movie.setImdbId(String.valueOf(omdbMovie.get("imdbID")));
		movie.setTitle(String.valueOf(omdbMovie.get("Title")));
		movie.setGenres(String.valueOf(omdbMovie.get("Genre")));
		movie.setRating(RATING);

		String releasedYearFull = String.valueOf(omdbMovie.get("Released"));

		if (NA.equals(releasedYearFull)) {
			movie.setReleasedYear(Integer.parseInt(String.valueOf(omdbMovie.get("Year"))));
		} else {
			movie.setReleasedYear(Integer.parseInt(releasedYearFull.substring(releasedYearFull.length() - 4)));
		}

		return movie;
	}

}