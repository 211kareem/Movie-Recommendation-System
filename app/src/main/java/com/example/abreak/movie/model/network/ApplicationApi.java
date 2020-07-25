package com.example.abreak.movie.model.network;

import com.example.abreak.movie.model.models.Details;
import com.example.abreak.movie.model.models.ExternalIds;
import com.example.abreak.movie.model.models.Hollywood;
import com.example.abreak.movie.model.models.MovieCast;
import com.example.abreak.movie.model.models.MovieTrailer;
import com.example.abreak.movie.model.models.PlayingMovies;
import com.example.abreak.movie.model.models.PouplarMovies;
import com.example.abreak.movie.model.models.RatedMovies;
import com.example.abreak.movie.model.models.MovieReview;
import com.example.abreak.movie.model.models.RecommendMovies;
import com.example.abreak.movie.model.models.SimilarMovies;
import com.example.abreak.movie.model.models.StarMovie;
import com.example.abreak.movie.model.models.UpComingMovies;
import io.reactivex.Observable;
import java.util.Map;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import static com.example.abreak.movie.utility.Constants.EXTERNAL_ID;
import static com.example.abreak.movie.utility.Constants.HOLLYWOOD_STARS;
import static com.example.abreak.movie.utility.Constants.LATEST;
import static com.example.abreak.movie.utility.Constants.MOVIE_CAST;
import static com.example.abreak.movie.utility.Constants.MOVIE_DATAILS;
import static com.example.abreak.movie.utility.Constants.MOVIE_RECOMMEND;
import static com.example.abreak.movie.utility.Constants.MOVIE_REVIEW;
import static com.example.abreak.movie.utility.Constants.MOVIE_SIMILAR;
import static com.example.abreak.movie.utility.Constants.MOVIE_TRAILERS;
import static com.example.abreak.movie.utility.Constants.NOW_PLAYING;
import static com.example.abreak.movie.utility.Constants.POPULAR;
import static com.example.abreak.movie.utility.Constants.STAR_MOVIES;
import static com.example.abreak.movie.utility.Constants.TOP_RATED;
import static com.example.abreak.movie.utility.Constants.UPCOMING;

public interface ApplicationApi {
  @GET(TOP_RATED) Observable<Response<RatedMovies>> getTopratedMovies(
      @QueryMap Map<String, String> params);

  @GET(POPULAR) Observable<Response<PouplarMovies>> getPopularMovies(
      @QueryMap Map<String, String> params);

  @GET(LATEST) Observable<Response<PouplarMovies>> getLatestMovies(
      @QueryMap Map<String, String> params);

  @GET(UPCOMING) Observable<Response<UpComingMovies>> getUpcomingMovies(
      @QueryMap Map<String, String> params);

  @GET(NOW_PLAYING) Observable<Response<PlayingMovies>> getNowPlayingMovies(
      @QueryMap Map<String, String> params);

  @GET(MOVIE_DATAILS) Observable<Response<Details>> getMovieDetails(@Path("movie_id") int id
      , @QueryMap Map<String, String> params);

  @GET(MOVIE_CAST) Observable<Response<MovieCast>> getMovieCast(@Path("movie_id") int id
      , @QueryMap Map<String, String> params);

  @GET(MOVIE_REVIEW) Observable<Response<MovieReview>> getMovieReview(@Path("movie_id") int id,
      @QueryMap Map<String, String> params);

  @GET(MOVIE_TRAILERS) Observable<Response<MovieTrailer>> getTrailers(@Path("movie_id") int id,
      @QueryMap Map<String, String> params);

  @GET(MOVIE_SIMILAR) Observable<Response<SimilarMovies>> getSimilarMovies(@Path("movie_id") int id,
      @QueryMap Map<String, String> params);

  @GET(MOVIE_RECOMMEND) Observable<Response<RecommendMovies>> getRecommendedMovies(
      @Path("movie_id") int id,
      @QueryMap Map<String, String> params);

  @GET(STAR_MOVIES) Observable<Response<StarMovie>> getStarMovie(@Path("person_id") int id,
      @QueryMap Map<String, String> params);

  @GET(EXTERNAL_ID) Observable<Response<ExternalIds>> getExteranl(@Path("movie_id") int id,
      @QueryMap Map<String, String> params);

  @GET(HOLLYWOOD_STARS) Observable<Response<Hollywood>> hollywoodStars(@QueryMap Map<String, String> params);
}
