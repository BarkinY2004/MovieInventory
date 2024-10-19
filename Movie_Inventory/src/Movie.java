import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int year;
    private String title;
    private String genre;
    private String director;
    private List<Actor> actors;

    // Constructor with parameters
    public Movie(int year, String title, String genre, String director, List<Actor> actors) {
        this.year = year;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
    }
    // Constructor without parameters
    public Movie() {
        this.year = 0;
        this.title = "";
        this.genre = "";
        this.director = "";
        this.actors = new ArrayList<>();
    }
    // Copy constructor
    public Movie(Movie other) {
        this.year = other.year;
        this.title = other.title;
        this.genre = other.genre;
        this.director = other.director;
        this.actors = new ArrayList<>(other.actors);
    }
    //here are the getters and setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
    // this is the toString method
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(year).append(", ").append(title).append(", ").append(genre).append(", ").append(director)
                .append(", {");
        for (int i = 0; i < actors.size(); i++) {
            builder.append(actors.get(i));
            if (i != actors.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append("}");
        return builder.toString();
    }

}
