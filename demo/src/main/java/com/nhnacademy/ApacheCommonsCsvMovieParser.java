package com.nhnacademy;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;

public class ApacheCommonsCsvMovieParser implements MovieParser {
    List<Movie> movieCsvList = new ArrayList<Movie>();

    @Override
    public List<Movie> parse(String fileName) throws IOException {
        try {
            CSVParser parser = CSVParser.parse(new InputStreamReader(getMovieFileAsStream()), CSVFormat.EXCEL);
            Object movieId = 0;
            Object title = "";
            Object generes = "";

            for (CSVRecord csvRecord : parser) {
                if (csvRecord.get(0).equals("movieId") || csvRecord.get(0).equals("title")
                        || csvRecord.get(0).equals(generes)) {

                } else {
                    Movie movie = new Movie();
                    movie.setMovieId(csvRecord.get(0));
                    movie.setTitle(csvRecord.get(1));
                    movie.setGeneres(csvRecord.get(2));
                    movieCsvList.add(movie);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieCsvList;
    }

}