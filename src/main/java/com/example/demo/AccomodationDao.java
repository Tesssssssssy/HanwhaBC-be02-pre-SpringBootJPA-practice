package com.example.demo;

import com.example.demo.model.GetAccomodationRes;
import com.example.demo.model.PatchAccomodationReq;
import com.example.demo.model.PostAccomodationReq;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AccomodationDao {

    private JdbcTemplate jdbcTemplate;

    public AccomodationDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createAccomodation(PostAccomodationReq postAccomodationReq) {
        String sql = "INSERT INTO Accomodation (name, address, latitude, longitude, content, max_user, price, img, " +
                                                    "hasAirConditioner, hasWashingMachine) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Object[] sqlParams = new Object[]{postAccomodationReq.getName(), postAccomodationReq.getAddress(),
                                            postAccomodationReq.getLatitude(), postAccomodationReq.getLongitude(),
                                            postAccomodationReq.getContent(), postAccomodationReq.getMax_user(),
                                            postAccomodationReq.getPrice(), postAccomodationReq.getImg(),
                                            postAccomodationReq.getHasAirConditioner(), postAccomodationReq.getHasWashingMachine()};

        jdbcTemplate.update(sql, sqlParams);
    }

    public GetAccomodationRes findAccomodation(int id) {
        String sql = "SELECT id, user_id, name, address, latitude, longitude, content, max_user, " +
                     "price, img, hasAirConditioner, hasWashingMachine, register_time " +
                     "FROM Accomodation " +
                     "WHERE is_active=0 AND id=?";
        return jdbcTemplate.queryForObject(
                sql,
                BeanPropertyRowMapper.newInstance(GetAccomodationRes.class),
                id
        );
    }

    public List<GetAccomodationRes> findAccomodationList() {
        String sql = "SELECT id, user_id, name, address, latitude, longitude, content, max_user, " +
                     "price, img, hasAirConditioner, hasWashingMachine, register_time " +
                     "FROM Accomodation " +
                     "WHERE is_active=0";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> GetAccomodationRes.builder()
                        .id(rs.getInt("id"))
                        .user_id(rs.getInt("user_id"))
                        .name(rs.getString("name"))
                        .address(rs.getString("address"))
                        .latitude(rs.getString("latitude"))
                        .longitude(rs.getString("longitude"))
                        .content(rs.getString("content"))
                        .max_user(rs.getInt("max_user"))
                        .price(rs.getInt("price"))
                        .img(rs.getString("img"))
                        .hasAirConditioner(rs.getInt("hasAirConditioner"))
                        .hasWashingMachine(rs.getInt("hasWashingMachine"))
                        .register_time(rs.getDate("register_time"))
                        .build()
        );
    }


    public void updateAccomodation(PatchAccomodationReq patchAccomodationReq, int id) {
        String sql = "UPDATE Accomodation SET name=?, address=?, latitude=?, longitude=?, " +
                     "content=?, max_user=?, price=?, img=?, hasAirConditioner=?, hasWashingMachine=? " +
                     "WHERE id=?";
        jdbcTemplate.update(sql, patchAccomodationReq.getName(), patchAccomodationReq.getAddress(), patchAccomodationReq.getLatitude(),
                patchAccomodationReq.getLongitude(), patchAccomodationReq.getContent(), patchAccomodationReq.getMax_user(),
                patchAccomodationReq.getPrice(), patchAccomodationReq.getImg(), patchAccomodationReq.getHasAirConditioner(),
                patchAccomodationReq.getHasWashingMachine(), id);
    }


    public void deleteAccomodation(int id) {
        String sql = "UPDATE Accomodation " +
                "SET is_active = 1 " +
                "WHERE id=?";

        jdbcTemplate.update(sql, id);
    }
}
