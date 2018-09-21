package com.movie.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movie.model.Movie;

@Repository
public class MovieDAOImpl implements MovieDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Movie> getAllMovies() {
		return sessionFactory.getCurrentSession().createQuery("from Movie").list();
	}

	@Override
	@Transactional
	public List<Movie> getMovieByImdbId(String imdbId) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Movie.class);
		c.add(Restrictions.eq("imdbId", imdbId));

		return c.list();
	}

	@Override
	@Transactional
	public List<Movie> getMovieByTitle(String title) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Movie.class);
		c.add(Restrictions.eq("title", title));

		return c.list();
	}

	@Override
	@Transactional
	public List<Movie> getMovieByYearRange(int startYear, int endYear) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Movie.class);
		c.add(Restrictions.ge("releasedYear", startYear));
		c.add(Restrictions.le("releasedYear", endYear));

		return c.list();
	}

	@Override
	@Transactional
	public List<Movie> getMovieByYear(int year) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Movie.class);
		c.add(Restrictions.eq("releasedYear", year));

		return c.list();
	}

	@Override
	@Transactional
	public List<Movie> getMovieByHigherRating(int rating) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Movie.class);
		c.add(Restrictions.ge("rating", rating));

		return c.list();
	}

	@Override
	@Transactional
	public List<Movie> getMovieByLowerRating(int rating) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Movie.class);
		c.add(Restrictions.le("rating", rating));

		return c.list();
	}

	@Override
	@Transactional
	public List<Movie> getMovieByGenre(String genre) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Movie.class);
		c.add(Restrictions.like("genres", genre, MatchMode.ANYWHERE));

		return c.list();
	}

	@Override
	@Transactional
	public void updateMovieRating(Movie movie) {

		String imdbId = movie.getImdbId();
		int rating = movie.getRating();
		List<Movie> movieList = getMovieByImdbId(imdbId);
		Movie movieUpdate;
		if (!movieList.isEmpty()) {
			movieUpdate = movieList.get(0);
			movieUpdate.setRating(rating);
			sessionFactory.getCurrentSession().flush();
		}
	}

	@Override
	@Transactional
	public void updateMovieGenres(Movie movie) {

		String imdbId = movie.getImdbId();
		String genres = movie.getGenres();
		List<Movie> movieList = getMovieByImdbId(imdbId);
		Movie movieUpdate;
		if (!movieList.isEmpty()) {
			movieUpdate = movieList.get(0);
			movieUpdate.setGenres(genres);
			sessionFactory.getCurrentSession().flush();
		}
	}

	@Override
	@Transactional
	public void addOmdbMovie(Movie movie) {
		sessionFactory.getCurrentSession().save(movie);
	}

}
