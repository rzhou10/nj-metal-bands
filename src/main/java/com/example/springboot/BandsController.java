package com.example.springboot;

import com.google.common.base.Strings;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.hibernate.DBInfo;
import com.example.interfaces.*;

@CrossOrigin
@RestController
public class BandsController {

    @GetMapping("/fetch-bands/{sortOrder}")
    public ResponseEntity<ArrayList<Band>> fetchBands(@PathVariable("sortOrder") String sortOrder) {
        // TODO: Add pagination
        String selectBands = "SELECT * FROM band order by ?";

        // Dotenv dotenv = getEnv();

        DBInfo info = new DBInfo();

        String connectionUrl = "jdbc:mysql://localhost:3306/" + info.getDbName() + "?serverTimezone=UTC";

        // TODO: Figure out why environment variables don't work
        // String connectionUrl = String.format("%1$s%2$s", dotenv.get("URL"),
        // dotenv.get("DATABASE"));

        try {
            Connection conn = DriverManager.getConnection(connectionUrl, info.getUser(), info.getPass());
            PreparedStatement ps = conn.prepareStatement(selectBands);
            ps.setString(1, sortOrder);
            ResultSet rs = ps.executeQuery();
            ArrayList<Band> results = new ArrayList<Band>();

            while (rs.next()) {
                Band b = new Band(rs.getString(2), rs.getInt(3), rs.getString(5), rs.getString(6));
                results.add(b);
            }

            return ResponseEntity.status(HttpStatus.OK).body(results);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<Band>(0));
        }
    }

    @CrossOrigin
    @PostMapping(value = "/insert-bands", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String insertBands(@RequestBody Band entity) {
        try {
            if (Strings.isNullOrEmpty(entity.getBandName())) {
                return "band_name field is missing from request body or empty.";
            }

            // this does not work, will fix in future iteration, going to use a class until
            // I get it to work
            // Dotenv dotenv = Dotenv.configure()
            //         .directory(".env")
            //         .ignoreIfMissing()
            //         .ignoreIfMalformed()
            //         .load();

            try {
                DBInfo info = new DBInfo();

                String connectionUrl = "jdbc:mysql://localhost:3306/" + info.getDbName() + "?serverTimezone=UTC";
                String selectBands = "Insert into nj_metal.band (band_name, year_formation, genre, city) values (?, ?, ?, ?)";
                Connection conn = DriverManager.getConnection(connectionUrl, info.getUser(), info.getPass());

                PreparedStatement ps = conn.prepareStatement(selectBands);
                ps.setString(1, entity.getBandName());
                ps.setInt(2, entity.getYear());
                ps.setString(3, entity.getGenre());
                ps.setString(4, entity.getCity());

                int rs = ps.executeUpdate();
                if (rs == 1) {
                    return "Horray!";
                }

                return "Failed to add bands!";
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
