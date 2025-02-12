package com.github.msufred.sms.data.controllers;

import com.github.msufred.sms.data.DailySummary;
import com.github.msufred.sms.data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailySummaryController implements ModelController<DailySummary> {

    private final Database database;

    public DailySummaryController(Database database) {
        this.database = database;
    }

    @Override
    public boolean insert(DailySummary model) throws SQLException {
        String sql = String.format("INSERT INTO daily_summaries (date, forwarded, revenues, expenses, cash_in, cash_out, " +
                "balance, tag, date_created, date_updated) VALUES ('%s', '%f', '%f', '%f', '%f', '%f', '%f', '%s', '%s', '%s')",
                model.getDate(), model.getForwarded(), model.getRevenues(), model.getExpenses(), model.getCashIn(),
                model.getCashOut(), model.getBalance(), model.getTag(), model.getDateCreated(), model.getDateUpdated());
        return database.executeQuery(sql);
    }

    @Override
    public boolean update(DailySummary model) throws SQLException {
        String sql = String.format("UPDATE daily_summaries SET date='%s', forwarded='%f', revenues='%f', " +
                "expenses='%f', cash_in='%f', cash_out='%f', balance='%f', tag='%s', date_updated='%s' WHERE id='%d'",
                model.getDate(), model.getForwarded(), model.getRevenues(), model.getExpenses(), model.getCashIn(),
                model.getCashOut(), model.getBalance(), model.getTag(), LocalDateTime.now(), model.getId());
        return database.executeQuery(sql);
    }

    @Override
    public boolean update(int id, String col, String value) throws SQLException {
        String sql = String.format("UPDATE daily_summaries SET %s='%s', date_updated='%s' WHERE id='%d'",
                col, value, LocalDateTime.now(), id);
        return database.executeQuery(sql);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return update(id, "date_deleted", LocalDateTime.now().toString());
    }

    public boolean deleteAll() throws SQLException {
        String sql = "DELETE FROM daily_summaries";
        return database.executeQuery(sql);
    }

    @Override
    public DailySummary get(int id) throws SQLException {
        String sql = String.format("SELECT * FROM daily_summaries WHERE date_deleted IS NULL AND id='%d'", id);
        try (ResultSet rs = database.executeQueryWithResult(sql)) {
            if (rs.next()) return fetchInfo(rs);
        }
        return null;
    }

    @Override
    public ObservableList<DailySummary> getAll() throws SQLException {
        String sql = "SELECT * FROM daily_summaries WHERE date_deleted IS NULL";
        ObservableList<DailySummary> list = FXCollections.observableArrayList();
        try (ResultSet rs = database.executeQueryWithResult(sql)) {
            while (rs.next()) list.add(fetchInfo(rs));
        }
        return list;
    }

    public DailySummary getByDate(LocalDate date) throws SQLException {
        String sql = String.format("SELECT * FROM daily_summaries WHERE date_deleted IS NULL AND date='%s'", date);
        try (ResultSet rs = database.executeQueryWithResult(sql)) {
            if (rs.next()) return fetchInfo(rs);
        }
        return null;
    }

    private DailySummary fetchInfo(ResultSet rs) throws SQLException {
        int index = 1;
        DailySummary dailySummary = new DailySummary();
        dailySummary.setId(rs.getInt(index++));
        dailySummary.setDate(rs.getDate(index++).toLocalDate());
        dailySummary.setForwarded(rs.getDouble(index++));
        dailySummary.setRevenues(rs.getDouble(index++));
        dailySummary.setExpenses(rs.getDouble(index++));
        dailySummary.setCashIn(rs.getDouble(index++));
        dailySummary.setCashOut(rs.getDouble(index++));
        dailySummary.setBalance(rs.getDouble(index++));
        dailySummary.setTag(rs.getString(index++));
        dailySummary.setDateCreated(rs.getTimestamp(index++).toLocalDateTime());
        dailySummary.setDateUpdated(rs.getTimestamp(index++).toLocalDateTime());
        Timestamp dateDeleted = rs.getTimestamp(index);
        if (dateDeleted != null) dailySummary.setDateDeleted(dateDeleted.toLocalDateTime());
        return dailySummary;
    }
}
