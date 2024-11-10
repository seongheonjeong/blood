package com.example.blood.repository;


import com.example.blood.dto.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcPatientRepository {
    private final DataSource dataSource;

    @Autowired
    public JdbcPatientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<PatientDto> findByDiseaseName(String diseaseName) {
        String sql = "select * from 환우 where 병명= ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {

            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,diseaseName);
            rs = preparedStatement.executeQuery();

            List<PatientDto> patientDtoList = new ArrayList<>();
            while (rs.next()) {
                PatientDto patientDto = new PatientDto();
                patientDto.setPatientId(rs.getString("환자ID"));
                patientDto.setName(rs.getString("이름"));
                java.sql.Date birthDateSql = rs.getDate("생년월일"); // 시간정보 없애기
                if (birthDateSql != null) {
                    patientDto.setBirth(birthDateSql.toLocalDate()); // LocalDate로 변환(dto 멤버변수 형식 맞춰줌)
                }
                patientDto.setPhoneNumber(rs.getString("휴대폰번호"));
                patientDto.setGender(rs.getString("성별"));
                patientDto.setHospitalName(rs.getString("병원이름"));
                patientDto.setDiseaseName(rs.getString("병명"));
                patientDtoList.add(patientDto);
            }
            return patientDtoList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(connection, preparedStatement, rs);
        }
    }
    public List<PatientDto> findByPatientName(String patientName) {
        String sql = "select * from 환우 where 이름= ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {

            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,patientName);
            rs = preparedStatement.executeQuery();

            List<PatientDto> patientDtoList = new ArrayList<>();
            while (rs.next()) {
                PatientDto patientDto = new PatientDto();
                patientDto.setPatientId(rs.getString("환자ID"));
                patientDto.setName(rs.getString("이름"));
                java.sql.Date birthDateSql = rs.getDate("생년월일"); // 시간정보 없애기
                if (birthDateSql != null) {
                    patientDto.setBirth(birthDateSql.toLocalDate()); // LocalDate로 변환(dto 멤버변수 형식 맞춰줌)
                }
                patientDto.setPhoneNumber(rs.getString("휴대폰번호"));
                patientDto.setGender(rs.getString("성별"));
                patientDto.setHospitalName(rs.getString("병원이름"));
                patientDto.setDiseaseName(rs.getString("병명"));
                patientDtoList.add(patientDto);
            }
            return patientDtoList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(connection, preparedStatement, rs);
        }
    }
    public List<PatientDto> findAll() {
        String sql = "select * from 환우";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            List<PatientDto> patientDtoList = new ArrayList<>();
            while (rs.next()) {
                PatientDto patientDto = new PatientDto();
                patientDto.setPatientId(rs.getString("환자ID"));
                patientDto.setName(rs.getString("이름"));
                java.sql.Date birthDateSql = rs.getDate("생년월일"); // 시간정보 없애기
                if (birthDateSql != null) {
                    patientDto.setBirth(birthDateSql.toLocalDate()); // LocalDate로 변환(dto 멤버변수 형식 맞춰줌)
                }
                patientDto.setPhoneNumber(rs.getString("휴대폰번호"));
                patientDto.setGender(rs.getString("성별"));
                patientDto.setHospitalName(rs.getString("병원이름"));
                patientDto.setDiseaseName(rs.getString("병명"));
                patientDtoList.add(patientDto);
            }
            return patientDtoList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(connection, preparedStatement, rs);
        }
    }

    //트랜잭션 경계 내에서 연결 일관성 유지
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection connection, PreparedStatement preparedStatement, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                close(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //DataSourceUtils를 사용하여 트랜잭션 경계 내에서 안전하게 Connection을 반환
    private void close(Connection connection) throws SQLException {
        DataSourceUtils.releaseConnection(connection, dataSource);
    }

 
}
