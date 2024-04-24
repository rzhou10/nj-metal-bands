package com.example.springboot;

import com.google.common.base.Strings;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.hibernate.DBInfo;
import com.example.interfaces.Band;

@RestController
public class BandsController {

    @GetMapping("/fetch-bands")
    public ArrayList<Band> fetchBands(@RequestBody String sortOrder) {
        String sqlSelectAllPersons = "SELECT * FROM nj_metal.band";

        Dotenv dotenv = getEnv();

        System.out.println(dotenv.get("URL"));
        DBInfo info = new DBInfo();

        String connectionUrl = "jdbc:mysql://localhost:3306/" + info.getDbName() + "?serverTimezone=UTC";

        // String connectionUrl = String.format("%1$s%2$s", dotenv.get("URL"), dotenv.get("DATABASE"));

        try (Connection conn = DriverManager.getConnection(connectionUrl, dotenv.get("MYSQL_USERNAME"),
                dotenv.get("MYSQL_PASSWORD"));
                PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
                ResultSet rs = ps.executeQuery()) {

            ArrayList<Band> results = new ArrayList<Band>();

            return results;

        } catch (SQLException e) {
            // handle the exception
            return new ArrayList<Band>(0);
        }
    }

    @PostMapping(value = "/insert-bands", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String insertBands(@RequestBody Band entity) {
        try {
            if (Strings.isNullOrEmpty(entity.getBandName())) {
                return "band_name field is missing from request body or empty.";
            }

            // this does not work, will fix in future iteration, going to use a class until
            // I get it to work
            Dotenv dotenv = Dotenv.configure()
                    .directory(".env")
                    .ignoreIfMissing()
                    .ignoreIfMalformed()
                    .load();

            try {
                DBInfo info = new DBInfo();

                String connectionUrl = "jdbc:mysql://localhost:3306/" + info.getDbName() + "?serverTimezone=UTC";
                String sqlSelectAllPersons = "Insert into nj_metal.band (band_name, year_formation, genre, city) values ('"
                        + entity.getBandName() + "', 0, '', '')";
                Connection conn = DriverManager.getConnection(connectionUrl, info.getUser(), info.getPass());
                PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
                int rs = ps.executeUpdate();
                if (rs == 1) {
                    return "Horray!";
                }
                ArrayList<Band> results = new ArrayList<Band>();

                return "Inserted bands!";
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                // handle the exception
                return "Failed to ";
            }
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Please make sure the band name is included in the request body");
        }
    }

    private Dotenv getEnv() {

        return Dotenv.configure()
                .directory("../../../../../../.env")
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();
    }

}
