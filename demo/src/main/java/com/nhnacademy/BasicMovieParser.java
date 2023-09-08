package com.nhnacademy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BasicMovieParser implements MovieParser {

    List<Movie> movieCsvList = new ArrayList<Movie>();

    @Override
    public List<Movie> parse(String fileName) throws IOException {

        // BufferedReader csvReader = new BufferedReader(
        // new FileReader("src/main/resources/movies.csv"));
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(getMovieFileAsStream()));

        try {
            String data;
            while ((data = csvReader.readLine()) != null) {// 파일이 끝이 났는가?
                String[] splitComma = data.split(",");

                if (splitComma.length >= 3) {
                    Movie movie = new Movie();
                    movie.setMovieId(splitComma[0]);
                    movie.setTitle(splitComma[1]);
                    movie.setGeneres(splitComma[2]);
                    movieCsvList.add(movie);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        csvReader.close();
        return movieCsvList;
    }

}